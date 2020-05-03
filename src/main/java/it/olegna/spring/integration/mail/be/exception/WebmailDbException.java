package it.olegna.spring.integration.mail.be.exception;

public class WebmailDbException extends Exception {

	private static final long serialVersionUID = 3231962465446163971L;

	public WebmailDbException() {
		super();
	}

	public WebmailDbException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebmailDbException(String message) {
		super(message);
	}

	public WebmailDbException(Throwable cause) {
		super(cause);
	}

	public WebmailDbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}
}