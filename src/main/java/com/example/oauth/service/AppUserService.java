package com.example.oauth.service;

import java.util.List;

import com.example.oauth.domain.backend.AppUserResponse;
import com.example.oauth.domain.bo.AppUser;
import com.example.oauth.domain.frontend.AppUserRequest;

public interface AppUserService {
	
	public AppUserResponse createUser(AppUserRequest request);
	
	public List<AppUserResponse> getUserResponses();
	
	public AppUserResponse getUserResponseById(long id);
	
	public AppUser getUserByEmail(String email);
}
