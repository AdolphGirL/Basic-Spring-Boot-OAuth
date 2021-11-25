package com.example.oauth.confiig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.oauth.domain.bo.AppUserAuthority;

/**
 * 自訂 API 的授權規則
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	UserDetailsService這個介面來注入，Spring會自動找到有實作這個介面的類別
//	也就是SpringSecurityUserServiceImpl
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * FOR 自訂義AuthenticationManagerBuilder
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/**
	 * 通常被使用在靜態資源控制
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	/**
	 * 訪問控制
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.antMatchers("/").permitAll()
			.antMatchers(HttpMethod.GET, "/api/books/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/books").permitAll()
			.antMatchers(HttpMethod.GET, "/api/users/**").hasAuthority(AppUserAuthority.ADMIN.name())
			.antMatchers(HttpMethod.POST, "/api/users").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.formLogin();
		
		http.headers().frameOptions().disable();
	}
	
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
////		Allow all requests to the root url (“/”)
//		http.authorizeRequests().antMatchers("/").permitAll()
////		Allow all requests to the H2 database console url (“/console/*”)
//		.and().authorizeRequests()
//				.antMatchers("/console/**").permitAll();
//		
		
		http.authorizeRequests()
//				「/users」這個 API 及其底下的所有 GET 請求，需通過身份驗證才可存取
				.antMatchers(HttpMethod.GET, "/api/books/**").authenticated()
//				其餘 API 的所有GET請求，允許所有呼叫方存取。
				.antMatchers(HttpMethod.GET).permitAll()
//				「/users」這個 API 的 POST 請求，允許所有呼叫方存取。
				.antMatchers(HttpMethod.POST, "/api/books").permitAll()
//				「/console」這個 API 及其底下的所有請求，允許所有呼叫方存取。
				.antMatchers("/console/**").permitAll()
//				其餘的所有 API，需通過身份驗證才可存取。
				.anyRequest().authenticated()
				.and()
//				Disable CSRF protection
				.csrf().disable()
//				 formLogin 則是啟用內建的登入畫面
				.formLogin();
		
//		Disable X-Frame-Options in Spring Security
		http.headers().frameOptions().disable();
	}
	*/
	
	
	
}
