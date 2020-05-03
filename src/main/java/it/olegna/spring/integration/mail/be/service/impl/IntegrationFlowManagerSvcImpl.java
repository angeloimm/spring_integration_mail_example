package it.olegna.spring.integration.mail.be.service.impl;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import javax.activation.DataSource;
import javax.annotation.PreDestroy;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;

import org.apache.commons.mail.util.MimeMessageParser;
import org.apache.commons.mail.util.MimeMessageUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.integration.StaticMessageHeaderAccessor;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.mail.dsl.ImapIdleChannelAdapterSpec;
import org.springframework.integration.mail.dsl.ImapMailInboundChannelAdapterSpec;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.mail.dsl.Pop3MailInboundChannelAdapterSpec;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.olegna.spring.integration.mail.be.dto.CasellaPostaleDto;
import it.olegna.spring.integration.mail.be.dto.ConfigMailInDto;
import it.olegna.spring.integration.mail.be.dto.ConfigurazioneCasellaPostaleDto;
import it.olegna.spring.integration.mail.be.events.MailInEvent;
import it.olegna.spring.integration.mail.be.mail.CasellaPostaleAuthenticator;
import it.olegna.spring.integration.mail.be.service.IIntegrationFlowManager;
import it.olegna.spring.integration.mail.be.util.MimeMessageInfoHolder;

@Service
public class IntegrationFlowManagerSvcImpl implements IIntegrationFlowManager {
	private static final Logger logger = LoggerFactory.getLogger(IntegrationFlowManagerSvcImpl.class.getName());
	public static final String MAIL_IN_FLOW_ID_PREFIX = "RICEZIONE_MAIL_FLOW_";
	@Autowired
	private IntegrationFlowContext flowContext;
	@Value("${webmail.be.imap.message.delete}")
	private boolean deleteMessages;
	@Value("${webmail.be.imap.mark.message.read}")
	private boolean markMessagesRead;
	@Value("${webmail.be.mail.debug}")
	private boolean mailDebug;
	@Value("${webmail.be.imap.polling.seconds}")
	private int pollingSeconds;
	@Value("${webmail.be.imap.max.message.per.poll}")
	private int maxMailMessagePerPoll;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	private Map<String, Closeable> closeables = new HashMap<String, Closeable>();
	@Override
	public void startMailReceiver(ConfigurazioneCasellaPostaleDto ccpd) throws Exception {
		avviaMailReceiver(ccpd);
	}

	private void avviaMailReceiver(ConfigurazioneCasellaPostaleDto ccpd ) {
		ConfigMailInDto confMailIn = ccpd.getConfigurazioneMailIngresso();
		if( logger.isInfoEnabled() ) {
			logger.info("Avvio mail receiver per la casella di posta {}", ccpd.getCasellaPostale().getIndirizzoMail());
		}
		if( confMailIn.isImap() ) {
			avviaImapMailReceiver(ccpd);
		}else {
			avviaPop3MailReceiver(ccpd);
		}
	}
	private void avviaImapMailReceiver(ConfigurazioneCasellaPostaleDto ccpd ) {
	logger.info("Avvio ImapMailReceiver");
		CasellaPostaleDto cpd = ccpd.getCasellaPostale();
		ConfigMailInDto confMailIn = ccpd.getConfigurazioneMailIngresso();
		String flowId = MAIL_IN_FLOW_ID_PREFIX+cpd.getIndirizzoMail();
		if( flowContext.getRegistrationById(flowId) != null ) {
			if( logger.isInfoEnabled() ) {
				logger.info("Integration flow con id {} già esistente. Lo rimuovo", flowId);
			}
			closeFolder(cpd);
			flowContext.remove(flowId);
		}
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.debug", String.valueOf(mailDebug));
		if( confMailIn.isRichiedeStarttsl() ) {
			javaMailProperties.setProperty("mail.imap.starttls.enable", "true");
		}
		if( confMailIn.isRichiedeSsl() ) {
			javaMailProperties.setProperty("mail.imap.ssl.enable", "true");

		}
		if( confMailIn.isRichiedeAutenticazione() ) {

			javaMailProperties.setProperty("mail.imap.auth", "true");
		}
		boolean idleSupport = true;
		StringBuilder connectionUrl = new StringBuilder();
		switch (confMailIn.getProtocollo()) {
		case IMAP:
			connectionUrl.append("imap://");
			javaMailProperties.setProperty("mail.store.protocol","imap");
			idleSupport = false;
			break;
		case IMAPS:
			connectionUrl.append("imaps://");
			javaMailProperties.setProperty("mail.store.protocol","imaps");
			idleSupport = false;
			break;
		case IMAP_IDLE:
			connectionUrl.append("imap://");
			javaMailProperties.setProperty("mail.store.protocol","imap");
			idleSupport = true;
			break;
		case IMAPS_IDLE:
			connectionUrl.append("imaps://");
			javaMailProperties.setProperty("mail.store.protocol","imaps");
			idleSupport = true;
			break;
		case POP3:
			if( logger.isWarnEnabled() ){
				logger.warn("Ricevuto POP3 nella configurazione IMAP. Qualcosa di sbagliato");
			}
			break;
		default:
			throw new IllegalArgumentException("Passato un protocollo non valido "+confMailIn.getProtocollo());
		}
		connectionUrl.append(URLEncoder.encode(cpd.getUsername(), Charset.forName("UTF-8")));
		connectionUrl.append(":");
		connectionUrl.append(URLEncoder.encode(cpd.getPassword(), Charset.forName("UTF-8")));
		connectionUrl.append("@");
		connectionUrl.append(confMailIn.getHost());
		connectionUrl.append(":");
		connectionUrl.append(confMailIn.getPorta());
		connectionUrl.append("/INBOX");
		Function<MimeMessage, Boolean> selectFunction = (MimeMessage message) -> {
			try {
				return true;
				/*
				Date dataRicezione = message.getReceivedDate();
				DateTime today = new DateTime().withTimeAtStartOfDay();
				DateTime tomorrow = today.plusDays(1).withTimeAtStartOfDay();
				if( logger.isInfoEnabled() ) {
					logger.info("Data ricezione {} today {} tomorrow {}", dataRicezione, today, tomorrow);
				}
				return dataRicezione.after(today.toDate()) && dataRicezione.before(tomorrow.toDate());
				 */
			}catch (Exception e) {
				logger.error("Errore nel recupero del messaggio in selectFunction", e);
				return false;
			}
		};

		IntegrationFlow flow = null;
		if( idleSupport ) {
			if( logger.isInfoEnabled() ){
				logger.info("Casella postale {} protocollo IMAP con supporto IDLE", confMailIn.getHost());
			}
			String userFlag = confMailIn.getHost() + "_idle_adapter";
			ImapIdleChannelAdapterSpec imapIdleChannelAdapterSpec = Mail.imapIdleAdapter(connectionUrl.toString())
					.javaMailProperties(javaMailProperties)
					.shouldDeleteMessages(deleteMessages)
					.shouldMarkMessagesAsRead(markMessagesRead) 
					.autoStartup(true)
					.autoCloseFolder(false)
					.userFlag(userFlag)
					.id(userFlag)
					//.searchTermStrategy(this::notSeenTerm)
					.selector(selectFunction);

			if (confMailIn.isRichiedeAutenticazione()) {
				imapIdleChannelAdapterSpec = imapIdleChannelAdapterSpec.javaMailAuthenticator(new CasellaPostaleAuthenticator(cpd.getIndirizzoMail(), cpd.getUsername(), cpd.getPassword()));
			}
			flow = IntegrationFlows
					.from(imapIdleChannelAdapterSpec)
					.handle(message ->{
						//Prendo il closable del messaggio e valorizzo i l'elenco di closeale da chiudere
						Closeable closeable = StaticMessageHeaderAccessor.getCloseableResource(message);
						if( !closeables.containsKey(cpd.getIndirizzoMail()) ) {
							closeables.put(cpd.getIndirizzoMail(), closeable);
						}
						publishMailEvent(message);
					})
					.get();
		}else {
			if( logger.isInfoEnabled() ){
				logger.info("Casella postale {} protocollo IMAP senza supporto IDLE", confMailIn.getHost());
			}
			String userFlag = confMailIn.getHost() + "_polling_adapter";
			ImapMailInboundChannelAdapterSpec adapterSpec = Mail.imapInboundAdapter(connectionUrl.toString())
					.javaMailProperties(javaMailProperties)
					.shouldDeleteMessages(deleteMessages)
					.shouldMarkMessagesAsRead(markMessagesRead)
					.autoCloseFolder(true)
					.userFlag(userFlag)
					.simpleContent(true)
					//.searchTermStrategy(this::notSeenTerm)
					.selector(selectFunction);
			if (confMailIn.isRichiedeAutenticazione()) {
				adapterSpec = adapterSpec.javaMailAuthenticator(new CasellaPostaleAuthenticator(cpd.getIndirizzoMail(), cpd.getUsername(), cpd.getPassword()));
			}

			flow = IntegrationFlows
					.from(adapterSpec, e -> e.poller(Pollers.fixedDelay(Duration.ofSeconds(pollingSeconds)).maxMessagesPerPoll(maxMailMessagePerPoll)))
					.handle(message ->{
						//Prendo il closable del messaggio e valorizzo i l'elenco di closeale da chiudere
						Closeable closeable = StaticMessageHeaderAccessor.getCloseableResource(message);
						if( !closeables.containsKey(cpd.getIndirizzoMail()) ) {
							closeables.put(cpd.getIndirizzoMail(), closeable);
						}
						publishMailEvent(message);
					})
					.get();
		}
		flowContext.registration(flow).id(flowId).register();
	}
	
	private void publishMailEvent( Message<?> message ) {
		try {
			//recupero il mime message e propago l'evento
			MimeMessage mimeMessage = (MimeMessage) message.getPayload();
			if( logger.isDebugEnabled() ) {

				try {
					mimeMessage.getAllHeaders().asIterator().forEachRemaining(header->{
						logger.debug("Header name {} header value {}", header.getName(), header.getValue());
					});
				} catch (javax.mail.MessagingException e) {
					logger.error("Errore nella lettura degli header", e);
				}
			}
			MimeMessageParser parser = new MimeMessageParser(mimeMessage);
			parser = parser.parse();
	        String msgId = mimeMessage.getMessageID();
			MimeMessageInfoHolder mmih = new MimeMessageInfoHolder();
			mmih.setFrom(parser.getFrom());
			mmih.setBccs(toStringAddress(parser.getBcc()));
			mmih.setCcs(toStringAddress(parser.getCc()));
			mmih.setTos(toStringAddress(parser.getTo()));
			//			MimeMessageBeanUtil mimeInfo = exctractInfo(mimeMessage);
			List<DataSource> allegati = parser.getAttachmentList();
			if( allegati != null && !allegati.isEmpty() ) {
				mmih.setAllegatiMail(allegati);
			}else {
				mmih.setAllegatiMail(Collections.emptyList());
			}
			String corpoMail = parser.getHtmlContent();
			if( !StringUtils.hasText(corpoMail) ) {
				corpoMail = parser.getPlainContent();
			}
			mmih.setMailReceivedDate(mimeMessage.getReceivedDate());

			mmih.setCorpoMail(corpoMail);
			mmih.setIdMessaggioMail(msgId);
			mmih.setOggettoMail(mimeMessage.getSubject());
			File emlFile = File.createTempFile(mimeMessage.getSubject().replaceAll("[^a-zA-Z0-9\\.\\-]", "_"), ".eml");
			MimeMessageUtils.writeMimeMessage(mimeMessage, emlFile);
			mmih.setMailEmlFile(emlFile);
			MailInEvent mie = new MailInEvent(this, mmih);
			applicationEventPublisher.publishEvent(mie);
		} catch (Exception e) {
			logger.error("Errore durante la pubblicazione dell'evento di ricezione mail {}", e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	private List<String> toStringAddress( List<Address> indirizzi ){
		if( indirizzi == null || indirizzi.isEmpty() ) {
			return Collections.emptyList();
		}
		List<String> result = new ArrayList<String>(indirizzi.size());
		indirizzi.forEach(mail -> {
			String indirizzoMail = mail.toString();
			if( mail instanceof InternetAddress ) {

				indirizzoMail = ((InternetAddress) mail).getAddress();
			}
			result.add(indirizzoMail);
		});
		return result;
	}
	private void closeFolder(CasellaPostaleDto cpd) {
		String indirizzoMail = cpd.getIndirizzoMail();
		if( closeables.containsKey(indirizzoMail) ) {
			Closeable closeable = closeables.get(indirizzoMail);
			try {
				if(closeable!=null) {closeable.close();}
			} catch (IOException e) {
				logger.warn("Errore nella chiusura del folder per l'indirizzo mail {}", indirizzoMail, e);
			}
		}
	}
	private void avviaPop3MailReceiver(ConfigurazioneCasellaPostaleDto ccpd ) {
		CasellaPostaleDto cpd = ccpd.getCasellaPostale();
		ConfigMailInDto confMailIn = ccpd.getConfigurazioneMailIngresso();
		String flowId = MAIL_IN_FLOW_ID_PREFIX+cpd.getIndirizzoMail();
		if( flowContext.getRegistrationById(flowId) != null ) {
			if( logger.isInfoEnabled() ) {
				logger.info("Integration flow con id {} già esistente. Lo rimuovo", flowId);
			}
			closeFolder(cpd);
			flowContext.remove(flowId);
		}
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.debug", String.valueOf(mailDebug));
		if( confMailIn.isRichiedeStarttsl() ) {
			javaMailProperties.setProperty("mail.pop3.starttls.enable", "true");
		}
		if( confMailIn.isRichiedeSsl() ) {
			javaMailProperties.setProperty("mail.pop3.ssl.enable", "true");

		}

		Function<MimeMessage, Boolean> selectFunction = (MimeMessage message) -> {
			try {
				Date dataRicezione = null;
				long timestamp = message.getHeader("timestamp") != null ? Long.parseLong(message.getHeader("timestamp")[0]) : 0;
				if( timestamp <= 0 ) {

					return true;
				}
				dataRicezione = new Date(timestamp);
				DateTime today = new DateTime().withTimeAtStartOfDay();
				DateTime tomorrow = today.plusDays(1).withTimeAtStartOfDay();
				if( logger.isInfoEnabled() ) {
					logger.info("Data ricezione {} today {} tomorrow {}", dataRicezione, today, tomorrow);
				}
				return dataRicezione.after(today.toDate()) && dataRicezione.before(tomorrow.toDate());
				//				return true;
			}catch (Exception e) {
				logger.error("Errore nel recupero del messaggio in selectFunction", e);
				return false;
			}
		};


		Pop3MailInboundChannelAdapterSpec pop3Spec = Mail.pop3InboundAdapter(confMailIn.getHost(), confMailIn.getPorta(), cpd.getUsername(), cpd.getPassword())
				.javaMailProperties(javaMailProperties)
				.shouldDeleteMessages(deleteMessages)
				.selector(selectFunction)
				.maxFetchSize(2);
		if( confMailIn.isRichiedeAutenticazione() ) {
			pop3Spec = pop3Spec.javaMailAuthenticator(new CasellaPostaleAuthenticator(cpd.getIndirizzoMail(), cpd.getUsername(), cpd.getPassword()));
		}
		IntegrationFlow flow = IntegrationFlows
				.from(pop3Spec, e -> e.poller(Pollers.fixedDelay(Duration.ofSeconds(pollingSeconds)).maxMessagesPerPoll(maxMailMessagePerPoll)))
				.handle(message -> {
					publishMailEvent(message);
				})

				.get();
		flowContext.registration(flow).id(flowId).register();
	}	
	private SearchTerm notSeenTerm(Flags supportedFlags, Folder folder) {

		return new FlagTerm(new Flags(Flags.Flag.SEEN), false);

	}	
	//	private SearchTerm fromAndNotSeenTerm(Flags supportedFlags, Folder folder) {
	//		try {
	//			FromTerm fromTerm = new FromTerm(new InternetAddress("bar@baz"));
	//			return new AndTerm(fromTerm, new FlagTerm(new Flags(Flags.Flag.SEEN), false));
	//		}
	//		catch (AddressException e) {
	//			throw new RuntimeException(e);
	//		}
	//
	//	}
	@PreDestroy
	public void preDestroy() {
		if( closeables != null && !closeables.isEmpty() ) {

			closeables.forEach( (key, value) ->{

				try {
					value.close();
				} catch (Exception e) {
					logger.warn("Errore nella chiusura del folder associato alla mail con indirizzo {}", key);
				}
			} );
		}
	}
}
