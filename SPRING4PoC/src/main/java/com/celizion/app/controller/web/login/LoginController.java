package com.celizion.app.controller.web.login;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * com.celizion.app.controller.web
 * LoginController.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 4.
 */
@Controller
public class LoginController {

//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value = "/loginForm.app", method = RequestMethod.GET)
	public String showLoginForm() {
		
		return "login/login";
	
	}
	
	@RequestMapping(value = "/accessDenied.app", method = RequestMethod.GET)
	public ModelAndView accessError() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMsg", messages.getMessage("error.access.error", null, Locale.getDefault()));
		mav.setViewName("errors/accessDenied");
		
		return mav;
	
	}
	
	@RequestMapping(value = "/sessionExpired.app", method = RequestMethod.GET)
	public ModelAndView sessionExpired() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMsg", messages.getMessage("error.session.expired", null, Locale.getDefault()));
		mav.setViewName("errors/sessionExpired");
		
		return mav;
	
	}

}
