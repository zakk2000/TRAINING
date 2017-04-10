package com.celizion.app.config.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <pre>
 * com.celizion.app.config.common
 * AppContext.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Configuration
@ComponentScan(basePackages = {"com.celizion.app.service", "com.celizion.app.dao", "com.celizion.app.config.security", "com.celizion.app.util"}, useDefaultFilters = false, includeFilters = {@Filter(Service.class), @Filter(Repository.class), @Filter(Configuration.class), @Filter(Component.class)})
public class AppContext extends WebMvcConfigurerAdapter {
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("/WEB-INF/messages/message-button"
				  					, "/WEB-INF/messages/message-label"
				  					, "/WEB-INF/messages/message-menu"
				  					, "/WEB-INF/messages/message-view");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);	// -1: Never Reload, 0: Always Reload
		
		return messageSource;
	
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	
	}

}
