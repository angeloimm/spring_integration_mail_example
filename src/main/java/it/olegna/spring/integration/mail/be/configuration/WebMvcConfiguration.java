package it.olegna.spring.integration.mail.be.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.olegna.spring.integration.mail.be.interceptors.MdcInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Autowired
	private MdcInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
	}
}