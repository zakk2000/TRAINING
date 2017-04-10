package com.celizion.app.config.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * <pre>
 * com.celizion.app.config.common
 * PropertySourcesPlaceholderConfig.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 12. 5.
 */
@Configuration
public class PropertySourcesPlaceholderConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		
		PropertySourcesPlaceholderConfigurer props = new PropertySourcesPlaceholderConfigurer();
		props.setLocations(new Resource[] {
				new ClassPathResource("/config/jdbc.properties")
				, new ClassPathResource("/config/conf.properties")
		});
		
		return props;
	
	}

}
