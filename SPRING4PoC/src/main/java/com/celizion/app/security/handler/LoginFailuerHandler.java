package com.celizion.app.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * com.celizion.app.security.handler
 * LoginFailuerHandler.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Component
public class LoginFailuerHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException, ServletException {
		
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>> LOGIN FAILUER");
		
//		setUseForward(true);
		if (authEx instanceof SessionAuthenticationException) {
			
			setDefaultFailureUrl("/sessionExpired.app");
		
		} else {
			
			setDefaultFailureUrl("/loginForm.app?error=1");
		
		}
		
		super.onAuthenticationFailure(request, response, authEx);
	
	}

}
