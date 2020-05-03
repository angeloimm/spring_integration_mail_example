package it.olegna.spring.integration.mail.be.web.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.olegna.spring.integration.mail.be.dto.BaseResponse;
import it.olegna.spring.integration.mail.be.dto.CasellaPostaleDto;
import it.olegna.spring.integration.mail.be.dto.ConfigMailInDto;
import it.olegna.spring.integration.mail.be.dto.ConfigMailOutDto;
import it.olegna.spring.integration.mail.be.dto.ConfigurazioneCasellaPostaleDto;
import it.olegna.spring.integration.mail.be.dto.ConfigurazioneCasellaPostaleResponseDto;
import it.olegna.spring.integration.mail.be.service.IIntegrationFlowManager;

@RestController
@RequestMapping("/casella-postale")
public class CasellaPostaleController {
	private static final Logger logger = LoggerFactory.getLogger(CasellaPostaleController.class.getName());
	public static final String OPERAZIONE_OK = "Operazione terminata correttamente";
	@Autowired
	private IIntegrationFlowManager integrationFlowManager;
	@RequestMapping(value = {"configura-casella"}, method = {RequestMethod.POST, RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse<ConfigurazioneCasellaPostaleResponseDto>> configuraCasellaPostale(@RequestBody ConfigurazioneCasellaPostaleDto requestBody ){
		HttpStatus status = HttpStatus.OK;
		String descrizioneEsito = OPERAZIONE_OK;
		CasellaPostaleDto cpd = requestBody.getCasellaPostale();
		ConfigMailInDto cmid = requestBody.getConfigurazioneMailIngresso();
		ConfigMailOutDto cmod = requestBody.getConfigurazioneMailUscita();
		if(!StringUtils.hasText(cpd.getIndirizzoMail())) {
			status = HttpStatus.BAD_REQUEST;
			descrizioneEsito = "Indirizzo mail casella postale non passato";
			BaseResponse<ConfigurazioneCasellaPostaleResponseDto> body = new BaseResponse<ConfigurazioneCasellaPostaleResponseDto>();
			body.setCodiceEsitoOperazione(status.value());
			body.setDescrizioneEsitoOperazione(descrizioneEsito);
			return ResponseEntity.status(status).body(body); 
		}
		if(!StringUtils.hasText(cpd.getUsername())) {
			status = HttpStatus.BAD_REQUEST;
			descrizioneEsito = "Username casella postale non passato";
			BaseResponse<ConfigurazioneCasellaPostaleResponseDto> body = new BaseResponse<ConfigurazioneCasellaPostaleResponseDto>();
			body.setCodiceEsitoOperazione(status.value());
			body.setDescrizioneEsitoOperazione(descrizioneEsito);
			return ResponseEntity.status(status).body(body); 
		}
		if(!StringUtils.hasText(cpd.getPassword())) {
			status = HttpStatus.BAD_REQUEST;
			descrizioneEsito = "Password casella postale non passata";
			BaseResponse<ConfigurazioneCasellaPostaleResponseDto> body = new BaseResponse<ConfigurazioneCasellaPostaleResponseDto>();
			body.setCodiceEsitoOperazione(status.value());
			body.setDescrizioneEsitoOperazione(descrizioneEsito);
			return ResponseEntity.status(status).body(body); 
		}
		if(!StringUtils.hasText(cmid.getHost())) {
			status = HttpStatus.BAD_REQUEST;
			descrizioneEsito = "Errore configurazione mail in: host non passato";
			BaseResponse<ConfigurazioneCasellaPostaleResponseDto> body = new BaseResponse<ConfigurazioneCasellaPostaleResponseDto>();
			body.setCodiceEsitoOperazione(status.value());
			body.setDescrizioneEsitoOperazione(descrizioneEsito);
			return ResponseEntity.status(status).body(body); 
		}
		if(cmid.getProtocollo() == null) {
			status = HttpStatus.BAD_REQUEST;
			descrizioneEsito = "Errore configurazione mail in: protocollo non passato; inviare solo uno dei seguenti valori ammissibili: IMAP, IMAP_IDLE, IMAPS, IMAPS_IDLE, POP3";
			BaseResponse<ConfigurazioneCasellaPostaleResponseDto> body = new BaseResponse<ConfigurazioneCasellaPostaleResponseDto>();
			body.setCodiceEsitoOperazione(status.value());
			body.setDescrizioneEsitoOperazione(descrizioneEsito);
			return ResponseEntity.status(status).body(body); 
		}
		if(!StringUtils.hasText(cmod.getHost())) {
			status = HttpStatus.BAD_REQUEST;
			descrizioneEsito = "Errore configurazione mail out: host non passato";
			BaseResponse<ConfigurazioneCasellaPostaleResponseDto> body = new BaseResponse<ConfigurazioneCasellaPostaleResponseDto>();
			body.setCodiceEsitoOperazione(status.value());
			body.setDescrizioneEsitoOperazione(descrizioneEsito);
			return ResponseEntity.status(status).body(body); 
		}
		if(cmod.getProtocollo() == null ) {
			status = HttpStatus.BAD_REQUEST;
			descrizioneEsito = "Errore configurazione mail out: protocollo non passato; inviare solo uno dei valori ammissibili: SMTP, SMTPS";
			BaseResponse<ConfigurazioneCasellaPostaleResponseDto> body = new BaseResponse<ConfigurazioneCasellaPostaleResponseDto>();
			body.setCodiceEsitoOperazione(status.value());
			body.setDescrizioneEsitoOperazione(descrizioneEsito);
			return ResponseEntity.status(status).body(body); 
		}
		BaseResponse<ConfigurazioneCasellaPostaleResponseDto> body = new BaseResponse<ConfigurazioneCasellaPostaleResponseDto>();
		try {
			
			this.integrationFlowManager.startMailReceiver(requestBody);
			ConfigurazioneCasellaPostaleResponseDto payload = new ConfigurazioneCasellaPostaleResponseDto();
			body.setPayload(payload);
		}catch (Exception e) {
			descrizioneEsito = "Errore nella configurazione della casella postale; "+e.getMessage();
			logger.error(descrizioneEsito, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		body.setCodiceEsitoOperazione(status.value());
		body.setDescrizioneEsitoOperazione(descrizioneEsito);
		return ResponseEntity.status(status).body(body);
	}
}