package it.olegna.spring.integration.mail.be.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.StringUtils;

import it.olegna.spring.integration.mail.be.enumerations.MailInEnum;

public class ConfigMailInDto extends AbstractDto {

	private static final long serialVersionUID = -955651093296154854L;
	private boolean imap;
	@NotBlank(message = "Host è un campo obbligatorio")
	private String host;
	@Positive(message = "La porta deve essere maggiore di 0")
	private int porta;
	private boolean richiedeSsl;
	@NotNull(message = "Indicare il tipo di protocollo da usare")
	private MailInEnum protocollo;
	private String prot;
	private boolean richiedeStarttsl;
	private boolean richiedeAutenticazione;
	public boolean isImap() {
		return imap;
	}
	public void setImap(boolean imap) {
		this.imap = imap;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public boolean isRichiedeSsl() {
		return richiedeSsl;
	}
	public void setRichiedeSsl(boolean richiedeSsl) {
		this.richiedeSsl = richiedeSsl;
	}
	public MailInEnum getProtocollo() {
		if( protocollo == null && StringUtils.hasText(prot) ) {
			if( prot.equals(MailInEnum.IMAP.getProtocol()) ) {
				protocollo = MailInEnum.IMAP;
			}else if( prot.equals(MailInEnum.IMAP_IDLE.getProtocol()) ){
				protocollo = MailInEnum.IMAP_IDLE;
			} else if( prot.equals(MailInEnum.IMAPS.getProtocol()) ) {
				protocollo = MailInEnum.IMAPS;
			}else if( prot.equals(MailInEnum.IMAPS_IDLE.getProtocol()) ){
				protocollo = MailInEnum.IMAPS_IDLE;
			}else if( prot.equals(MailInEnum.POP3.getProtocol()) ) {
				protocollo = MailInEnum.POP3;
			}
		}
		return protocollo;
	}
	public void setProtocollo(MailInEnum protocollo) {
		this.protocollo = protocollo;
	}
	
	public String getProt() {
		return prot;
	}
	public void setProt(String prot) {
		this.prot = prot;
	}
	public boolean isRichiedeStarttsl() {
		return richiedeStarttsl;
	}
	public void setRichiedeStarttsl(boolean richiedeStarttsl) {
		this.richiedeStarttsl = richiedeStarttsl;
	}
	public boolean isRichiedeAutenticazione() {
		return richiedeAutenticazione;
	}
	public void setRichiedeAutenticazione(boolean richiedeAutenticazione) {
		this.richiedeAutenticazione = richiedeAutenticazione;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + (imap ? 1231 : 1237);
		result = prime * result + porta;
		result = prime * result + ((prot == null) ? 0 : prot.hashCode());
		result = prime * result + ((protocollo == null) ? 0 : protocollo.hashCode());
		result = prime * result + (richiedeAutenticazione ? 1231 : 1237);
		result = prime * result + (richiedeSsl ? 1231 : 1237);
		result = prime * result + (richiedeStarttsl ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigMailInDto other = (ConfigMailInDto) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (imap != other.imap)
			return false;
		if (porta != other.porta)
			return false;
		if (prot == null) {
			if (other.prot != null)
				return false;
		} else if (!prot.equals(other.prot))
			return false;
		if (protocollo != other.protocollo)
			return false;
		if (richiedeAutenticazione != other.richiedeAutenticazione)
			return false;
		if (richiedeSsl != other.richiedeSsl)
			return false;
		if (richiedeStarttsl != other.richiedeStarttsl)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ConfigMailInDto [imap=" + imap + ", host=" + host + ", porta=" + porta + ", richiedeSsl=" + richiedeSsl
				+ ", protocollo=" + protocollo + ", prot=" + prot + ", richiedeStarttsl=" + richiedeStarttsl
				+ ", richiedeAutenticazione=" + richiedeAutenticazione + ", getPk()=" + getPk() + "]";
	}
}