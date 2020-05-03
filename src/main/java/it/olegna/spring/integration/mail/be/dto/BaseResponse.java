package it.olegna.spring.integration.mail.be.dto;
import java.io.Serializable;

public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = -6901048136970048368L;
	private long codiceEsitoOperazione;
	private String descrizioneEsitoOperazione;
	private T payload;
	public long getCodiceEsitoOperazione() {
		return codiceEsitoOperazione;
	}
	public void setCodiceEsitoOperazione(long codiceEsitoOperazione) {
		this.codiceEsitoOperazione = codiceEsitoOperazione;
	}
	public String getDescrizioneEsitoOperazione() {
		return descrizioneEsitoOperazione;
	}
	public void setDescrizioneEsitoOperazione(String descrizioneEsitoOperazione) {
		this.descrizioneEsitoOperazione = descrizioneEsitoOperazione;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "BaseResponse [codiceEsitoOperazione=" + codiceEsitoOperazione + ", descrizioneEsitoOperazione="
				+ descrizioneEsitoOperazione + ", payload=" + payload + "]";
	}
}
