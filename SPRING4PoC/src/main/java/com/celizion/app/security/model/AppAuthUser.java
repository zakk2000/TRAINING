package com.celizion.app.security.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <pre>
 * com.celizion.app.security.model
 * AppAuthUser.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
public class AppAuthUser implements UserDetails {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String userId;
	private String userPw;
	private String userName;
	private Set<GrantedAuthority> authorities;
	
	public String getUserId() {
		
		return getUsername();
	
	}

	public void setUserId(String userId) {
		
		this.userId = userId;
	
	}
	
	public String getUserPw() {
		
		return getPassword();
	
	}

	public void setUserPw(String userPw) {
		
		this.userPw = userPw;
	
	}
	
	public String getUserName() {
		
		return userName;
	
	}
	
	public void setUserName(String userName) {
		
		this.userName = userName;
	
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	
	}
	
	@Override
	public String getPassword() {
		
		return userPw;
	
	}
	
	@Override
	public String getUsername() {
		
		return userId;
	
	}
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	
	}
	
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	
	}
	
	@Override
	public boolean isEnabled() {
		
		return true;
	
	}
	
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		
		SortedSet<GrantedAuthority> sortedAuth = new TreeSet<GrantedAuthority>(new AuthorityComparator());
		
		for (GrantedAuthority auth : authorities) {
			
			sortedAuth.add(auth);
		
		}
		
		return sortedAuth;
	
	}
	
	private static class AuthorityComparator implements Comparator<GrantedAuthority> {
		
		@Override
		public int compare(GrantedAuthority ga1, GrantedAuthority ga2) {
			
			if (ga1.getAuthority() == null) {
				
				return 1;
			
			}
			
			if (ga2.getAuthority() == null) {
				
				return -1;
			
			}
			
			return ga1.getAuthority().compareTo(ga2.getAuthority());
		
		}
	
	}
	
	@Override
	public int hashCode() {
		
		return userId.hashCode();
	
	}
	
	@Override
	public boolean equals(Object rhs) {
		
		if (rhs instanceof AppAuthUser) {
			
			return userId.equals(((AppAuthUser) rhs).userId);
		
		}
		
		return false;
	
	}

}
