package com.celizion.app.controller.web.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <pre>
 * com.celizion.app.controller.web
 * ZipcodeController.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 28.
 */
@Controller
public class SearchWifiController {

	@RequestMapping(value = "/search.app", method = RequestMethod.GET)
	public String getList() {
		
		return "search/main";
	
	}

}
