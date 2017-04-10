package com.celizion.app.security.model;

import org.pojomatic.annotations.AutoProperty;

import com.celizion.app.model.common.CommonModel;

/**
 * <pre>
 * com.celizion.app.security.model
 * AppUserAuthority.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@AutoProperty
public class AppUserAuthority extends CommonModel {

	private String authority;
	
	public String getAuthority() {
		
		return authority;
	
	}
	
	public void setAuthority(String authority) {
		
		this.authority = authority;
	
	}

}
