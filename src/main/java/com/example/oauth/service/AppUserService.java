package com.example.oauth.service;

import com.example.oauth.domain.backend.AppUserResponse;
import com.example.oauth.domain.frontend.AppUserRequest;

public interface AppUserService {
	
	public AppUserResponse createUser(AppUserRequest request);

}
