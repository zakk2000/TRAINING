package com.celizion.app.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.celizion.app.mapper.security.AuthUserMapper;
import com.celizion.app.security.model.AppAuthUser;
import com.celizion.app.security.model.AppUserAuthority;

/**
 * <pre>
 * com.celizion.app.security.service
 * AppUserService.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
@Service
@Transactional(readOnly = true)
public class AppUserService extends JdbcDaoImpl {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private PasswordEncoder passwordEncoder;
	
	private AuthUserMapper authUserMapper;
	
	public PasswordEncoder getPasswordEncoder() {
		
		return passwordEncoder;
	
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		
		this.passwordEncoder = passwordEncoder;
	
	}
	
	public AuthUserMapper getUserAuthMapper() {
		
		return authUserMapper;
	
	}
	
	public void setUserAuthMapper(AuthUserMapper authUserMapper) {
		
		this.authUserMapper = authUserMapper;
	
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) {
		
		logger.debug("PASSWORD CHECK >>>>>>>>>>>>>>>>>> ENCODE : {}", passwordEncoder.encode("appuser01"));
		
		AppAuthUser loginUser = getUserInfoById(userId);
		if (loginUser != null) {
			
			Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
			
			if (getEnableAuthorities()) {
				
				authSet.addAll(loadUserAuthorities(loginUser.getUsername()));
			
			}
			
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(authSet);
			loginUser.setAuthorities(authList);
			
		}
		
		return loginUser;
	
	}
	
	private AppAuthUser getUserInfoById(String userId) {
		
		return authUserMapper.selectUserInfoById(userId);
		
		/*return getJdbcTemplate().queryForObject(getUsersByUsernameQuery(), new String[] {userId}, new RowMapper<AppAuthUser>() {
			
			public AppAuthUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				AppAuthUser user = new AppAuthUser();
				user.setUserId(rs.getString("USER_ID"));
				user.setUserPw(rs.getString("USER_PW"));
				user.setUserName(rs.getString("USER_NAME"));
				
				return user;
			
			}
		
		});*/
	
	}
	
	@Override
	protected List<GrantedAuthority> loadUserAuthorities(String userId) {
		
		logger.debug("ROLE PREFIX CHECK >>>>>>>>>>>>>>>>>> {}", getRolePrefix());

		List<AppUserAuthority> userAuthList = authUserMapper.selectUserAuthById(userId);
		
		List<GrantedAuthority> list = new LinkedList<GrantedAuthority>();
		
		for (AppUserAuthority auth : userAuthList) {
			
			list.add(new SimpleGrantedAuthority(getRolePrefix() + auth.getAuthority()));
		
		}
		
		return list; 
		
		/*return getJdbcTemplate().query(getAuthoritiesByUsernameQuery(), new String[] {userId}, new RowMapper<GrantedAuthority>() {
			
			public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return new SimpleGrantedAuthority(getRolePrefix() + rs.getString("AUTHORITY"));
			
			}
		
		});*/
	
	}
	
	/*public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceAuth() {
		
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceAuthMap = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
		
		List<AppResourceAuth> resultList = authUserMapper.selectResourceAuth();
		
		RequestMatcher resourcePattern;
		String tempStr = null;
		
		for (AppResourceAuth resourceAuth : resultList) {
			
			resourcePattern = new AntPathRequestMatcher(resourceAuth.getResourcePattern());
			List<ConfigAttribute> confAttrList = new LinkedList<ConfigAttribute>();
			
			if (resourceAuth.getResourcePattern() != null && resourceAuth.getResourcePattern().equals(tempStr)) {
				
				List<ConfigAttribute> tempList = resourceAuthMap.get(resourcePattern);
				for (ConfigAttribute confAttr : tempList) {
					
					confAttrList.add(confAttr);
				
				}
			
			}
						
			confAttrList.add(new SecurityConfig((String) resourceAuth.getResourceAuthority()));
			
			resourceAuthMap.put(resourcePattern, confAttrList);
			
			
			tempStr = resourceAuth.getResourcePattern();
		
		}
		
		// LinkedHashMap Print
		Set<?> set = resourceAuthMap.entrySet();
		Iterator<?> iter = set.iterator();
		while (iter.hasNext()) {
			
			logger.debug("LinkedHashMap<RequestMatcher, List<ConfigAttribute>> >>>>>>>>>>>>>>>>>>> {}", iter.next());
		
		}
		
		// LinkedHashMap Print
		Set<?> keys = resourceAuthMap.keySet();
		for (Object key : keys) {
			
			logger.debug("LinkedHashMap<RequestMatcher, List<ConfigAttribute>> >>>>>>>>>>>>>>>>>>> {}", resourceAuthMap.get(key));
			for (ConfigAttribute configAttr : resourceAuthMap.get(key)) {
				
				logger.debug("ConfigAttribute CHECK >>>>>>>>>>>>>>>>>>> {}", configAttr);
			
			}
		
		}
		
		return resourceAuthMap;
	
	}*/

}
