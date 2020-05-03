package it.olegna.spring.integration.mail.be.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import it.olegna.spring.integration.mail.be.events.MailInEvent;
import it.olegna.spring.integration.mail.be.handlers.MailMessageHandler;

@Component
public class MailInListener {
	
	private static final Logger logger = LoggerFactory.getLogger(MailInListener.class.getName());
	@Autowired
	private MailMessageHandler mailHandler;
	
	@EventListener(classes = {MailInEvent.class})
	public void handleMailInEvent( MailInEvent event ) {
		if( logger.isDebugEnabled() ) {
			logger.debug("Ricevuta mail in da sorgente {}", event.getSource());
		}
		try {
			mailHandler.handleMessage(event.getMailMessageInfoHolder());
		} catch (Exception e) {
			logger.error("Errore nella gestione del mime message {}", e.getMessage(), e);
		}
	}
}
