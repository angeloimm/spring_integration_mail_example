package it.olegna.spring.integration.mail.be.exception;

public class WebmailDocumentaleException extends  Exception{

	private static final long serialVersionUID = -1717426706366026988L;

	public WebmailDocumentaleException() {
        super();
    }

    public WebmailDocumentaleException(String message) {
        super(message);
    }

    public WebmailDocumentaleException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebmailDocumentaleException(Throwable cause) {
        super(cause);
    }
}
