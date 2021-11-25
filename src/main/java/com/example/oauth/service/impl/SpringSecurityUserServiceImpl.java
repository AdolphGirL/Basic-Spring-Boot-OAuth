package com.example.oauth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.oauth.domain.bo.AppUser;
import com.example.oauth.service.AppUserService;

/**
 * 1.	Security驗證時，會使用UserDetails介面的物件作為使用者載體資料。
 * 		程式專案中設計了AppUser類別，實作時仍需準備UserDetails的物件。
 * 
 * 2.	Spring Security的驗證作業實際是交由AuthenticationProvider的實作來執行（例如DaoAuthenticationProvider），
 * 		然後透過呼叫authenticate(Authentication authentication)進行驗證。
 * 		在Provider的驗證方法authenticate(Authentication authentication)中會先呼叫UserDetailsService.loadUserByUsername(String username)
 * 		查詢使用者資訊UserDetails，然後比對使用者資訊與輸入的密碼是否相同來驗證是否為合法的使用者。
 * 3.	解說參考 https://matthung0807.blogspot.com/2019/09/spring-security-userdetailsservice.html
 */
public class SpringSecurityUserServiceImpl implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityUserServiceImpl.class);
	
	@Autowired
	private AppUserService appUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			AppUser user = appUserService.getUserByEmail(username);
			if (user != null) {
				
				List<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
																.map(auth -> new SimpleGrantedAuthority(auth.name()))
																.collect(Collectors.toCollection(ArrayList::new));
				
				return new User(user.getEmailAddress(), user.getPassword(), authorities);
			} else {
				throw new UsernameNotFoundException("Username is wrong.");
			}
		} catch (Exception x) {
			LOGGER.error("[+] [loadUserByUsername] error: ", x);
			throw new UsernameNotFoundException("Username is wrong.");
		}
	}

}
