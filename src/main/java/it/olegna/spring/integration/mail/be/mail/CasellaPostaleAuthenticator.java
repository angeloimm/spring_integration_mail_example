package it.olegna.spring.integration.mail.be.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CasellaPostaleAuthenticator extends Authenticator {
	private static final Logger logger = LoggerFactory.getLogger(CasellaPostaleAuthenticator.class.getName());
	private String username;
	private String password;
	private String indirizzoMail;
	public CasellaPostaleAuthenticator(String indirizzoMail, String username, String password) {
		super();
		if( !StringUtils.hasText(indirizzoMail) ) {
			throw new IllegalArgumentException("Passato un indirizzo mail casella postale non valido ["+indirizzoMail+"]");
		}
		if( !StringUtils.hasText(username) ) {
			throw new IllegalArgumentException("Passata una username utente casella postale non valida ["+username+"]");
		}
		if( !StringUtils.hasText(password) ) {
			throw new IllegalArgumentException("Passata una password utente casella postale non valida ["+password+"]");
		}
		this.username = username;
		this.password = password;
		this.indirizzoMail = indirizzoMail;
	}
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		if( logger.isDebugEnabled() ) {
			logger.debug("AUTENTICAZIONE CASELLA POSTALE {} USERNAME {}",indirizzoMail, username);
		}
		PasswordAuthentication result = new PasswordAuthentication(username, password);
		return result;
	}	
}