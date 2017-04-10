package com.celizion.app.config.common;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * <pre>
 * com.celizion.app.config.common
 * MariaDBDataSource.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 28.
 */
@Configuration
@MapperScan(value = "com.celizion.app.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class MariaDBDataSource implements TransactionManagementConfigurer {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private @Value("${jdbc.driverClass}") String driverClass;
	private @Value("${jdbc.jdbcUrl}") String jdbcUrl;
	private @Value("${jdbc.user}") String user;
	private @Value("${jdbc.password}") String password;
	private @Value("${jdbc.initialPoolSize}") String initialPoolSize;
	private @Value("${jdbc.acquireIncrement}") String acquireIncrement;
	private @Value("${jdbc.maxPoolSize}") String maxPoolSize;
	private @Value("${jdbc.minPoolSize}") String minPoolSize;
	private @Value("${jdbc.checkoutTimeout}") String checkoutTimeout;
	
	@Bean(destroyMethod = "close")
	DataSource dataSource() {
		
		try {
			
			/*
			 * c3p0 Connection Pooling Library
			 */
			ComboPooledDataSource datasource = new ComboPooledDataSource();
			datasource.setDriverClass(driverClass);
			datasource.setJdbcUrl(jdbcUrl);
			datasource.setUser(user);
			datasource.setPassword(password);
			datasource.setInitialPoolSize(Integer.valueOf(initialPoolSize));
			datasource.setAcquireIncrement(Integer.valueOf(acquireIncrement));
			datasource.setMaxPoolSize(Integer.valueOf(maxPoolSize));
			datasource.setMinPoolSize(Integer.valueOf(minPoolSize));
			datasource.setCheckoutTimeout(Integer.valueOf(checkoutTimeout));
			
			return datasource;
		
		} catch (PropertyVetoException e) {
			
			logger.error("=================== An error occurred initializing DataSource ===================\n" + e.getMessage());
			throw new RuntimeException("=================== An error occurred initializing DataSource ===================\n", e);
		
		}
	
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setConfigLocation(new ClassPathResource("com/celizion/app/mapper/config/mybatis-config.xml"));
		
		return sessionFactory.getObject();
	
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		return new DataSourceTransactionManager(dataSource());
	
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		
		return transactionManager();
	
	}

}
