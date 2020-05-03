package it.olegna.spring.integration.mail.be.dto;

import java.io.Serializable;

public class ConfigurazioneCasellaPostaleDto implements Serializable {

	private static final long serialVersionUID = 2247407906378718039L;
	private CasellaPostaleDto casellaPostale;
	private ConfigMailInDto configurazioneMailIngresso;
	private ConfigMailOutDto configurazioneMailUscita;
	public CasellaPostaleDto getCasellaPostale() {
		return casellaPostale;
	}
	public void setCasellaPostale(CasellaPostaleDto casellaPostale) {
		this.casellaPostale = casellaPostale;
	}
	public ConfigMailInDto getConfigurazioneMailIngresso() {
		return configurazioneMailIngresso;
	}
	public void setConfigurazioneMailIngresso(ConfigMailInDto configurazioneMailIngresso) {
		this.configurazioneMailIngresso = configurazioneMailIngresso;
	}
	public ConfigMailOutDto getConfigurazioneMailUscita() {
		return configurazioneMailUscita;
	}
	public void setConfigurazioneMailUscita(ConfigMailOutDto configurazioneMailUscita) {
		this.configurazioneMailUscita = configurazioneMailUscita;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casellaPostale == null) ? 0 : casellaPostale.hashCode());
		result = prime * result + ((configurazioneMailIngresso == null) ? 0 : configurazioneMailIngresso.hashCode());
		result = prime * result + ((configurazioneMailUscita == null) ? 0 : configurazioneMailUscita.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigurazioneCasellaPostaleDto other = (ConfigurazioneCasellaPostaleDto) obj;
		if (casellaPostale == null) {
			if (other.casellaPostale != null)
				return false;
		} else if (!casellaPostale.equals(other.casellaPostale))
			return false;
		if (configurazioneMailIngresso == null) {
			if (other.configurazioneMailIngresso != null)
				return false;
		} else if (!configurazioneMailIngresso.equals(other.configurazioneMailIngresso))
			return false;
		if (configurazioneMailUscita == null) {
			if (other.configurazioneMailUscita != null)
				return false;
		} else if (!configurazioneMailUscita.equals(other.configurazioneMailUscita))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ConfigurazioneCasellaPostaleDto [casellaPostale=" + casellaPostale + ", configurazioneMailIngresso="
				+ configurazioneMailIngresso + ", configurazioneMailUscita=" + configurazioneMailUscita + "]";
	}
}
