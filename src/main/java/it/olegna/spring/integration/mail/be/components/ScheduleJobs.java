package it.olegna.spring.integration.mail.be.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class ScheduleJobs {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobs.class.getName());

    @Scheduled(cron = "${webmail.be.scheduling.retry.notification.cron}")
    public void ritentaInvioNotifica(){
        StopWatch sw = new StopWatch("RE-INVIO NOTIFICA");

    }
}
