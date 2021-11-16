package com.example.oauth.domain.backend;

import java.util.List;
import com.example.oauth.domain.bo.AppUserAuthority;
import lombok.Data;

@Data
public class AppUserResponse {
	
	private String id;
	private String emailAddress;
	private String name;
	private List<AppUserAuthority> authorities;

}
