package com.celizion.app.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * <pre>
 * com.celizion.app.config.web
 * AppWebConfig.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.celizion.app.controller.web"}, useDefaultFilters = false, includeFilters = {@Filter(Controller.class)})
public class AppWebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		
		TilesConfigurer tilesConfig = new TilesConfigurer();
		tilesConfig.setDefinitions("/WEB-INF/tiles/tiles-def.xml", "/WEB-INF/tiles/tiles-views-def.xml");
		tilesConfig.setCheckRefresh(true);
		
		return tilesConfig;
	
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		
		return tilesViewResolver;
	
	}
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	
	}
	
	@Bean
	public BoardExcelView BoardExcelView() {
		
		return new BoardExcelView();
	
	}

}
