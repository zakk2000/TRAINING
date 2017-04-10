package com.celizion.app.controller.rest.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.celizion.app.model.search.Wifi;
import com.celizion.app.service.search.SearchWifiService;

/**
 * <pre>
 * com.celizion.app.controller.rest.search
 * SearchWifiRestController.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 31.
 */
@RestController
@RequestMapping(value = "/search")
public class SearchWifiRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SearchWifiService searchWifiService;
	
	@RequestMapping(value = "/{searchKeyword}", method = RequestMethod.GET)
	public List<Wifi> getSearchResults(@PathVariable("searchKeyword") String searchKeyword) {
		
		logger.debug("PARAMETER CHECK in CONTROLLER >>>>>>>>>>>>>>>>>>>>> {}", searchKeyword);
		return searchWifiService.searchWifi(searchKeyword);
	
	}

}
