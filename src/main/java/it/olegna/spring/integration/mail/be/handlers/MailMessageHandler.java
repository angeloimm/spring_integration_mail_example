package it.olegna.spring.integration.mail.be.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.olegna.spring.integration.mail.be.util.MimeMessageInfoHolder;

@Component
public class MailMessageHandler {
	private static final Logger logger = LoggerFactory.getLogger(MailMessageHandler.class.getName());


	public void handleMessage(MimeMessageInfoHolder mimeMessage) throws Exception {


		//		//Recupero i dati del messaggio
		//		MimeMessageParser parser = new MimeMessageParser(mimeMessage);
		//		parser = parser.parse();
		

	}

}