package it.olegna.spring.integration.mail.be.configuration;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletContainerConfig {
	@Bean
	public UndertowServletWebServerFactory embeddedServletContainerFactory() {
		return new UndertowServletWebServerFactory();
	}
}
