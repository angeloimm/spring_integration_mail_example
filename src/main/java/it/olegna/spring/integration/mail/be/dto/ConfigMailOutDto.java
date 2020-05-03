package it.olegna.spring.integration.mail.be.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import it.olegna.spring.integration.mail.be.enumerations.MailOutEnum;

public class ConfigMailOutDto extends AbstractDto {

	private static final long serialVersionUID = 8120260557854216337L;
	@NotBlank(message = "Host è un campo obbligatorio")
	private String host;
	private boolean richiedeSsl;
	private boolean richiedeTls;
	private int portaSsl;
	private int portaTls;
	@NotNull(message = "Indicare il tipo di protocollo da usare")
	private MailOutEnum protocollo;
	private String prot;
	private boolean richiedeStarttsl;
	private boolean richiedeAutenticazione;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public boolean isRichiedeSsl() {
		return richiedeSsl;
	}
	public void setRichiedeSsl(boolean richiedeSsl) {
		this.richiedeSsl = richiedeSsl;
	}
	public boolean isRichiedeTls() {
		return richiedeTls;
	}
	public void setRichiedeTls(boolean richiedeTls) {
		this.richiedeTls = richiedeTls;
	}
	public int getPortaSsl() {
		return portaSsl;
	}
	public void setPortaSsl(int portaSsl) {
		this.portaSsl = portaSsl;
	}
	public int getPortaTls() {
		return portaTls;
	}
	public void setPortaTls(int portaTls) {
		this.portaTls = portaTls;
	}
	public MailOutEnum getProtocollo() {
		if( protocollo == null && StringUtils.hasText(prot) ) {
			if( prot.equals(MailOutEnum.SMTP.getProtocol()) ) {
				protocollo = MailOutEnum.SMTP;
			}else {
				protocollo = MailOutEnum.SMTPS;
			}
		}
		return protocollo;
	}
	public void setProtocollo(MailOutEnum protocollo) {
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
		result = prime * result + portaSsl;
		result = prime * result + portaTls;
		result = prime * result + ((prot == null) ? 0 : prot.hashCode());
		result = prime * result + ((protocollo == null) ? 0 : protocollo.hashCode());
		result = prime * result + (richiedeAutenticazione ? 1231 : 1237);
		result = prime * result + (richiedeSsl ? 1231 : 1237);
		result = prime * result + (richiedeStarttsl ? 1231 : 1237);
		result = prime * result + (richiedeTls ? 1231 : 1237);
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
		ConfigMailOutDto other = (ConfigMailOutDto) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (portaSsl != other.portaSsl)
			return false;
		if (portaTls != other.portaTls)
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
		if (richiedeTls != other.richiedeTls)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ConfigMailOutDto [host=" + host + ", richiedeSsl=" + richiedeSsl + ", richiedeTls=" + richiedeTls
				+ ", portaSsl=" + portaSsl + ", portaTls=" + portaTls + ", protocollo=" + protocollo + ", prot=" + prot
				+ ", richiedeStarttsl=" + richiedeStarttsl + ", richiedeAutenticazione=" + richiedeAutenticazione
				+ ", getPk()=" + getPk() + "]";
	}
}