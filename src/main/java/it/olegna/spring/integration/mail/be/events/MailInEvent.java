package it.olegna.spring.integration.mail.be.events;

import org.springframework.context.ApplicationEvent;

import it.olegna.spring.integration.mail.be.util.MimeMessageInfoHolder;

public class MailInEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5168107269200915273L;
	private MimeMessageInfoHolder mailMessageInfoHolder;
	public MailInEvent(Object source, MimeMessageInfoHolder mailMessageInfoHolder) {
		super(source);
		this.mailMessageInfoHolder = mailMessageInfoHolder;
	}
	public MimeMessageInfoHolder getMailMessageInfoHolder() {
		return mailMessageInfoHolder;
	}
}