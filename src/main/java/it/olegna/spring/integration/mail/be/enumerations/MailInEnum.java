package it.olegna.spring.integration.mail.be.enumerations;

public enum MailInEnum {
	IMAP("imap", false),
	IMAP_IDLE("imap_idle", true),
	IMAPS("imaps", false),
	IMAPS_IDLE("imaps_idle", true),
	POP3("pop3", false);
	private String protocol;
	private boolean idle;
	private MailInEnum(String protocol, boolean idle) {

		this.protocol = protocol;
		this.idle = idle;
	}

	public String getProtocol() {
		return protocol;
	}

	public boolean isIdle() {
		return idle;
	}
}
