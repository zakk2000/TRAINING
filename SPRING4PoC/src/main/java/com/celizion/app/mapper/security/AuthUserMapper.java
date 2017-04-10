package com.celizion.app.mapper.security;

import java.util.List;

import com.celizion.app.security.model.AppAuthUser;
import com.celizion.app.security.model.AppResourceAuth;
import com.celizion.app.security.model.AppUserAuthority;

/**
 * <pre>
 * com.celizion.app.mapper.security
 * AuthUserMapper.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2016. 8. 3.
 */
public interface AuthUserMapper {
	
	AppAuthUser selectUserInfoById(String userId);
	List<AppUserAuthority> selectUserAuthById(String userId);
	List<AppResourceAuth> selectResourceAuth();

}
