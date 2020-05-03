package it.olegna.spring.integration.mail.be.enumerations;

public enum MailOutEnum {
	SMTP("smtp"),
	SMTPS("smtps");
	
	private String protocol;

	private MailOutEnum(String protocol) {
		this.protocol = protocol;
	}

	public String getProtocol() {
		return protocol;
	}
}