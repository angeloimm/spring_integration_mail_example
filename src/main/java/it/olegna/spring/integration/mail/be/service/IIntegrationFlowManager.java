package it.olegna.spring.integration.mail.be.service;

import it.olegna.spring.integration.mail.be.dto.ConfigurazioneCasellaPostaleDto;

public interface IIntegrationFlowManager {
	
	void startMailReceiver(ConfigurazioneCasellaPostaleDto ccpd) throws Exception;
}
