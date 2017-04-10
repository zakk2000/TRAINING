package com.celizion.app.config.security;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.celizion.app.mapper.security.AuthUserMapper;
import com.celizion.app.security.service.AppUserService;

/**
 * <pre>
 * com.celizion.app.config.security
 * AppSecurityConfig.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.celizion.app.security.handler"})
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AuthenticationSuccessHandler successHandler;
	
	@Autowired
	AuthenticationFailureHandler failureHandler;
	
	@Autowired
	AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	AuthUserMapper authUserMapper;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/resources/**", "/loginForm.app", "/sessionExpired.app").permitAll()
				.antMatchers("/loginForm.app").anonymous()
				.antMatchers("/rest/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
				.antMatchers("/**/*.app").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
				.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
				.and()
			.formLogin()
				.loginPage("/loginForm.app")
				.loginProcessingUrl("/j_spring_security_check")
				.usernameParameter("userId")
				.passwordParameter("userPw")
				.successHandler(successHandler)
				.failureHandler(failureHandler)
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/loginForm.app")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and()
			.csrf()
				.disable();
		
		http.addFilterBefore(concurrencyFilter(), ConcurrentSessionFilter.class)
			.addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.sessionManagement()
				.sessionAuthenticationStrategy(customCompositeSessionAuthenticationStrategy());
	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		
		authBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	
	}
	
	@Override
	protected UserDetailsService userDetailsService() {
		
		return appUserService();
	
	}
	
	private UserDetailsService appUserService() {
		
		AppUserService appUserService = new AppUserService();
		appUserService.setDataSource(dataSource);
		appUserService.setRolePrefix("ROLE_");
		appUserService.setEnableGroups(false);
		appUserService.setPasswordEncoder(passwordEncoder());
		appUserService.setUserAuthMapper(authUserMapper);
		
		return appUserService;
	
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
		
		logger.debug("SessionRegistry REGISTERED >>>>>>>>>>>>>>>>>>>>>>>>>>");
		return new SessionRegistryImpl();
	
	}
	
	@Bean
	public ConcurrentSessionFilter concurrencyFilter() {
		
		logger.debug("ConcurrentSessionFilter REGISTERED >>>>>>>>>>>>>>>>>>>>>>>>>>");
		return new ConcurrentSessionFilter(sessionRegistry());
	
	}
	
	@Bean
	public UsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
		
		logger.debug("UsernamePasswordAuthenticationFilter REGISTERED >>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		UsernamePasswordAuthenticationFilter upaf = new UsernamePasswordAuthenticationFilter();
		upaf.setSessionAuthenticationStrategy(customCompositeSessionAuthenticationStrategy());
		upaf.setAuthenticationManager(authenticationManagerBean());
		
		return upaf;
	
	}
	
	@Bean
	public CompositeSessionAuthenticationStrategy customCompositeSessionAuthenticationStrategy() {
		
		logger.debug("CompositeSessionAuthenticationStrategy REGISTERED >>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		List<SessionAuthenticationStrategy> strategyList = new LinkedList<SessionAuthenticationStrategy>();
		
		ConcurrentSessionControlAuthenticationStrategy cscas = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
		cscas.setMaximumSessions(1);
		cscas.setExceptionIfMaximumExceeded(true);
		
		strategyList.add(cscas);
		strategyList.add(new SessionFixationProtectionStrategy());
		strategyList.add(new RegisterSessionAuthenticationStrategy(sessionRegistry()));
		
		return new CompositeSessionAuthenticationStrategy(strategyList);
		
	}

}
