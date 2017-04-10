package com.celizion.app.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * <pre>
 * com.celizion.app.config.security
 * SecurityWebApplicationInitializer.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 9. 5.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	
	@Override
	protected boolean enableHttpSessionEventPublisher() {
		
		return true;
	
	}

}
