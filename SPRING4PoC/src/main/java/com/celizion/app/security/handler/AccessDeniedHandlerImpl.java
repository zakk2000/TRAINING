package com.celizion.app.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * com.celizion.app.security.handler
 * AccessDeniedHandlerImpl.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		if (request.getUserPrincipal() != null && request.getRequestURI().contains("/loginForm.app")) {
			
			logger.debug("RETRY LOGIN >>>>>>>>>>>>>>>>>>>>>>>>");
			
			redirectStrategy.sendRedirect(request, response, "/index.app");
		
		} else {
			
			logger.debug("NOT ENOUGH AUTHORITY >>>>>>>>>>>>>>>>>>>>>>>>");
			
			redirectStrategy.sendRedirect(request, response, "/accessDenied.app");
		
		}
	
	}

}
