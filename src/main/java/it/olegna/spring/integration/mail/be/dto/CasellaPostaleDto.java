package it.olegna.spring.integration.mail.be.dto;

import javax.validation.constraints.NotBlank;

public class CasellaPostaleDto extends AbstractDto {

	private static final long serialVersionUID = 6184441832982449155L;
	@NotBlank(message = "Indirizzo mail è un campo obbligatorio")
	private String indirizzoMail;
	@NotBlank(message = "Username è un campo obbligatorio")
	private String username;
	@NotBlank(message = "Password è un campo obbligatorio")
	private String password;
	private String nomeVisualizzato;
	private boolean pec;
	private boolean casellaAttiva;
	private String idFolderDocumentale;
	public String getIndirizzoMail() {
		return indirizzoMail;
	}
	public void setIndirizzoMail(String indirizzoMail) {
		this.indirizzoMail = indirizzoMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNomeVisualizzato() {
		return nomeVisualizzato;
	}
	public void setNomeVisualizzato(String nomeVisualizzato) {
		this.nomeVisualizzato = nomeVisualizzato;
	}
	public boolean isPec() {
		return pec;
	}
	public void setPec(boolean pec) {
		this.pec = pec;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdFolderDocumentale() {
		return idFolderDocumentale;
	}

	public void setIdFolderDocumentale(String idFolderDocumentale) {
		this.idFolderDocumentale = idFolderDocumentale;
	}
	public boolean isCasellaAttiva() {
		return casellaAttiva;
	}
	public void setCasellaAttiva(boolean casellaAttiva) {
		this.casellaAttiva = casellaAttiva;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (casellaAttiva ? 1231 : 1237);
		result = prime * result + ((idFolderDocumentale == null) ? 0 : idFolderDocumentale.hashCode());
		result = prime * result + ((indirizzoMail == null) ? 0 : indirizzoMail.hashCode());
		result = prime * result + ((nomeVisualizzato == null) ? 0 : nomeVisualizzato.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (pec ? 1231 : 1237);
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		CasellaPostaleDto other = (CasellaPostaleDto) obj;
		if (casellaAttiva != other.casellaAttiva)
			return false;
		if (idFolderDocumentale == null) {
			if (other.idFolderDocumentale != null)
				return false;
		} else if (!idFolderDocumentale.equals(other.idFolderDocumentale))
			return false;
		if (indirizzoMail == null) {
			if (other.indirizzoMail != null)
				return false;
		} else if (!indirizzoMail.equals(other.indirizzoMail))
			return false;
		if (nomeVisualizzato == null) {
			if (other.nomeVisualizzato != null)
				return false;
		} else if (!nomeVisualizzato.equals(other.nomeVisualizzato))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pec != other.pec)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CasellaPostaleDto [indirizzoMail=" + indirizzoMail + ", username=" + username + ", password=" + password
				+ ", nomeVisualizzato=" + nomeVisualizzato + ", pec=" + pec + ", casellaAttiva=" + casellaAttiva
				+ ", idFolderDocumentale=" + idFolderDocumentale + "]";
	}
}