package it.olegna.spring.integration.mail.be.util;

import javax.mail.internet.MimeMessage;

public class MailOutUtil {
    private String messageId;
    private MimeMessage mailMessage;

    public MailOutUtil(String messageId, MimeMessage mailMessage) {
        this.messageId = messageId;
        this.mailMessage = mailMessage;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MimeMessage getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(MimeMessage mailMessage) {
        this.mailMessage = mailMessage;
    }
}
