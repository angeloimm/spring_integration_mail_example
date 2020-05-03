package it.olegna.spring.integration.mail.be.interceptors;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class MdcInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(MdcInterceptor.class.getName());
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	throws Exception {
		try {
		MDC.clear();

		MDC.put("microservice", "webmail.be");
		MDC.put("REMOTE_HOST_ID", request.getRemoteHost());
		MDC.put("UUID_ID", UUID.randomUUID().toString());
		MDC.put("REQUEST_PATH_ID", request.getRequestURL().toString());
		
		}
		catch (Exception e) {
			
			logger.warn("Errore nel settaggio MDC {}", e.getMessage(), e);
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {
//		MDC.remove("USER_ID"        );
//		MDC.remove("TOKEN_ID"       );
//		MDC.remove("REQUEST_PATH_ID");
//		MDC.remove("UUID_ID"        );
//		MDC.remove("REMOTE_HOST_ID" );
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
}
