package it.olegna.spring.integration.mail.be;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.olegna.spring.integration.mail.Application;
import it.olegna.spring.integration.mail.be.service.IIntegrationFlowManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestStartImap {
	private static final Logger logger = LoggerFactory.getLogger(TestStartImap.class.getName());
	@Autowired
	private IIntegrationFlowManager flowMgr;
	@Test
	public void testStartMailReceiver(){
		try{
			flowMgr.startMailReceiver();
			while (true) {
				int i = 0;
				
			}
		}catch (Exception e){
			logger.error("Errore nella configurazione della casella postale; {}", e.getMessage(), e);
		}
	}
}
