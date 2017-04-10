package com.celizion.app.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * com.celizion.app.security.handler
 * LoginSuccessHandler.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>> LOGIN SUCCESS");
		
		setDefaultTargetUrl("/index.app");
		
		super.onAuthenticationSuccess(request, response, auth);
	
	}

}
