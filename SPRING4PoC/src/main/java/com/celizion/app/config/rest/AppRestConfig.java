package com.celizion.app.config.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <pre>
 * com.celizion.app.config.rest
 * AppRestConfig.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.celizion.app.controller.rest", "com.celizion.app.exception"}, useDefaultFilters = false, includeFilters = {@Filter(Controller.class), @Filter(ControllerAdvice.class)})
public class AppRestConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		// for JSON
		converters.add(mappingJacksonHttpMessageConverter());
		
		// for XML
		converters.add(marshallingHttpMessageConverter());
	
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		configurer.favorPathExtension(true)
				  .useJaf(true)
				  .favorParameter(true)
				  .parameterName("bodyFormat")
				  .ignoreAcceptHeader(false)
				  .defaultContentType(MediaType.APPLICATION_JSON)
				  .mediaType("json", MediaType.APPLICATION_JSON)
				  .mediaType("xml", MediaType.APPLICATION_XML);
	
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		// If you want to use this option, remove the comments.
		// for JSON Root Element
		/*ObjectMapper oMapper = converter.getObjectMapper();
		oMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		oMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);*/
		
		// using XML Root Element in JSON
		/*JaxbAnnotationModule jaModule = new JaxbAnnotationModule();
		oMapper.registerModule(jaModule);*/
		
		converter.setPrettyPrint(true);
		
		return converter;
	
	}
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(new String[] {"com.celizion.app.model"});
		
		Map<String, Object> marshallerProperties = new HashMap<String, Object>();
		marshallerProperties.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		marshaller.setMarshallerProperties(marshallerProperties);
		
		return marshaller;
	
	}
	
	@Bean
	public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
		
		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setMarshaller(jaxb2Marshaller());
		converter.setUnmarshaller(jaxb2Marshaller());
		
		return converter;
	
	}

}
