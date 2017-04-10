package com.celizion.app.model.common;

import org.pojomatic.Pojomatic;

/**
 * <pre>
 * com.celizion.app.model.common
 * CommonModel.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
public class CommonModel {

	@Override
	public boolean equals(Object obj) {
		
		return Pojomatic.equals(this, obj);
	
	}
	
	@Override
	public int hashCode() {
		
		return Pojomatic.hashCode(this);
	
	}
	
	@Override
	public String toString() {
		
		return Pojomatic.toString(this);
	
	}

}
