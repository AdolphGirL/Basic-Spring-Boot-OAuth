package com.example.oauth.domain.frontend;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.example.oauth.domain.bo.AppUserAuthority;

import lombok.Data;

@Data
public class AppUserRequest {
	
	@NotBlank
	private String emailAddress;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String name;
	
	@NotEmpty
	private List<AppUserAuthority> authorities;
	
}
