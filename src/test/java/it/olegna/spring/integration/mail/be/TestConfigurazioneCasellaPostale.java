package it.olegna.spring.integration.mail.be;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.olegna.spring.integration.mail.be.dto.BaseResponse;
import it.olegna.spring.integration.mail.be.dto.ConfigurazioneCasellaPostaleDto;
import it.olegna.spring.integration.mail.be.dto.ConfigurazioneCasellaPostaleResponseDto;

public class TestConfigurazioneCasellaPostale {
	private static final Logger logger = LoggerFactory.getLogger(TestConfigurazioneCasellaPostale.class.getName());
	private static final String BASE_UR = "http://localhost:8080";
	@Test
	public void testConfigurazioneCasellaPostale(){
		try{
			ConfigurazioneCasellaPostaleDto ccpd = new ConfigurazioneCasellaPostaleDto();
			//String casellaPostaleJson = System.getenv("CONFIGURAZIONE_CASELLA_JSON");
			String casellaPostaleJson = System.getenv("CONFIGURAZIONE_CASELLA_PEC_JSON");

			if(StringUtils.hasText(casellaPostaleJson)){
				ObjectMapper om = new ObjectMapper();
				ccpd = om.readValue(casellaPostaleJson, ConfigurazioneCasellaPostaleDto.class);
				ccpd.getCasellaPostale().setCasellaAttiva(true);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<ConfigurazioneCasellaPostaleDto> requestEntity = new HttpEntity<>(ccpd, headers);
				String url = BASE_UR+"/casella-postale/configura-casella";
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<BaseResponse<ConfigurazioneCasellaPostaleResponseDto>> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<BaseResponse<ConfigurazioneCasellaPostaleResponseDto>>(){});
				if( response.getStatusCode().is2xxSuccessful() ){
					ConfigurazioneCasellaPostaleResponseDto ccprd = response.getBody().getPayload();
					if( logger.isInfoEnabled() ){
						logger.info("Ottenuto ccprd {}", ccprd);
					}
				}
			}
		}catch (Exception e){
			logger.error("Errore nella configurazione della casella postale; {}", e.getMessage(), e);
		}
	}
}
