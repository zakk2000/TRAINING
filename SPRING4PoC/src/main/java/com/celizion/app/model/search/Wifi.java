package com.celizion.app.model.search;

import org.pojomatic.annotations.AutoProperty;

import com.celizion.app.model.common.CommonModel;

/**
 * <pre>
 * com.celizion.app.search.model
 * Zipcode.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 28.
 */
@AutoProperty
public class Wifi extends CommonModel {

	private long id;
	/*private String bssid;
	private String ssid;
	private String loc_x;
	private String loc_y;
	private String buildingid;
	private String maxrssi;*/
	private String city;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
