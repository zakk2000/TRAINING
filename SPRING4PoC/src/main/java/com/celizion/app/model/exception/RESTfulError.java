package com.celizion.app.model.exception;

import javax.xml.bind.annotation.XmlRootElement;

import org.pojomatic.annotations.AutoProperty;

import com.celizion.app.model.common.CommonModel;

/**
 * <pre>
 * com.celizion.app.model.exception
 * RESTfulError.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@AutoProperty
@XmlRootElement(name = "error")
public class RESTfulError extends CommonModel {
	
	private int errorCode;
	private String errorMessage;
	
	public RESTfulError() {}
	
	public RESTfulError(int errorCode, String errorMessage) {
		
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	
	}
	
	public int getErrorCode() {
		
		return errorCode;
	
	}
	
	public void setErrorCode(int errorCode) {
		
		this.errorCode = errorCode;
	
	}
	
	public String getErrorMessage() {
		
		return errorMessage;
	
	}
	
	public void setErrorMessage(String errorMessage) {
		
		this.errorMessage = errorMessage;
	
	}

}
