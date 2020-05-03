package it.olegna.spring.integration.mail.be.util;

import java.util.List;
import java.util.Optional;

import javax.activation.DataSource;

public class MimeMessageBeanUtil {
	private String mailContent;
	private Optional<List<DataSource>> allegati;
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public Optional<List<DataSource>> getAllegati() {
		return allegati;
	}
	public void setAllegati(Optional<List<DataSource>> allegati) {
		this.allegati = allegati;
	}
	@Override
	public String toString() {
		return "MimeMessageBeanUtil [mailContent=" + mailContent + ", allegati=" + allegati + "]";
	}
}