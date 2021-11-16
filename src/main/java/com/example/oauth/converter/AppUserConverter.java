package com.example.oauth.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.example.oauth.domain.backend.AppUserResponse;
import com.example.oauth.domain.bo.AppUser;
import com.example.oauth.domain.frontend.AppUserRequest;

public class AppUserConverter {
	
	public static AppUser toAppUser(AppUserRequest request) {
		AppUser user = new AppUser();
		user.setEmailAddress(request.getEmailAddress());
		user.setPassword(request.getPassword());
		user.setName(request.getName());
		user.setAuthorities(request.getAuthorities());

		return user;
	}

	public static AppUserResponse toAppUserResponse(AppUser user) {
		AppUserResponse response = new AppUserResponse();
		response.setId(String.valueOf(user.getId()));
		response.setEmailAddress(user.getEmailAddress());
		response.setName(user.getName());
		response.setAuthorities(user.getAuthorities());
		
		return response;
	}
	
	public static List<AppUserResponse> toAppUserResponses(List<AppUser> users) {
		return users.stream()
				.map(AppUserConverter::toAppUserResponse)
				.collect(Collectors.toList());
	}

}
