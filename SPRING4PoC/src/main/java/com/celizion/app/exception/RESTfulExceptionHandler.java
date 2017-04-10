package com.celizion.app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.celizion.app.model.exception.RESTfulError;

/**
 * <pre>
 * com.celizion.app.exception
 * RESTfulExceptionHandler.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@ControllerAdvice
public class RESTfulExceptionHandler {

	@ExceptionHandler(value = {ResourceNotFoundException.class})
	protected ResponseEntity<RESTfulError> handleResourceNotFoundEntityException(ResourceNotFoundException rneEx, WebRequest request) {
		
		RESTfulError rfError = new RESTfulError(404, "NOT FOUND");
		
		return new ResponseEntity<RESTfulError>(rfError, new HttpHeaders(), HttpStatus.NOT_FOUND);
	
	}

}
