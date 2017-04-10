package com.celizion.app.security.model;

import org.pojomatic.annotations.AutoProperty;

import com.celizion.app.model.common.CommonModel;

/**
 * <pre>
 * com.celizion.app.security.model
 * AppResourceAuth.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@AutoProperty
public class AppResourceAuth extends CommonModel {

	private String resourcePattern;
	private String resourceAuthority;
	
	public String getResourcePattern() {
		
		return resourcePattern;
	
	}
	
	public void setResourcePattern(String resourcePattern) {
		
		this.resourcePattern = resourcePattern;
	
	}
	
	public String getResourceAuthority() {
		
		return resourceAuthority;
	
	}
	
	public void setResourceAuthority(String resourceAuthority) {
		
		this.resourceAuthority = resourceAuthority;
	
	}

}
