package it.olegna.spring.integration.mail.be.dto;
import java.io.Serializable;
import java.util.Objects;

public class ConfigurazioneCasellaPostaleResponseDto implements Serializable {

	private static final long serialVersionUID = -2614981662522774237L;
	private String idCasellaPostale;
    private String nomeTopiRicezioneMessaggi;
    private String indirizzoMail;
    public ConfigurazioneCasellaPostaleResponseDto() {
    }

    public ConfigurazioneCasellaPostaleResponseDto(String idCasellaPostale, String nomeTopiRicezioneMessaggi, String indirizzoMail) {
        this.idCasellaPostale = idCasellaPostale;
        this.nomeTopiRicezioneMessaggi = nomeTopiRicezioneMessaggi;
        this.indirizzoMail = indirizzoMail;
    }

    public String getIdCasellaPostale() {
        return idCasellaPostale;
    }

    public void setIdCasellaPostale(String idCasellaPostale) {
        this.idCasellaPostale = idCasellaPostale;
    }

    public String getNomeTopiRicezioneMessaggi() {
        return nomeTopiRicezioneMessaggi;
    }

    public void setNomeTopiRicezioneMessaggi(String nomeTopiRicezioneMessaggi) {
        this.nomeTopiRicezioneMessaggi = nomeTopiRicezioneMessaggi;
    }

    public String getIndirizzoMail() {
        return indirizzoMail;
    }

    public void setIndirizzoMail(String indirizzoMail) {
        this.indirizzoMail = indirizzoMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurazioneCasellaPostaleResponseDto that = (ConfigurazioneCasellaPostaleResponseDto) o;
        return Objects.equals(idCasellaPostale, that.idCasellaPostale) &&
                Objects.equals(nomeTopiRicezioneMessaggi, that.nomeTopiRicezioneMessaggi) &&
                Objects.equals(indirizzoMail, that.indirizzoMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCasellaPostale, nomeTopiRicezioneMessaggi, indirizzoMail);
    }

    @Override
    public String toString() {
        return "ConfigurazioneCasellaPostaleResponseDto{" +
                "idCasellaPostale='" + idCasellaPostale + '\'' +
                ", nomeTopiRicezioneMessaggi='" + nomeTopiRicezioneMessaggi + '\'' +
                ", indirizzoMail='" + indirizzoMail + '\'' +
                '}';
    }
}