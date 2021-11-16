package com.example.oauth.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.oauth.domain.backend.AppUserResponse;
import com.example.oauth.domain.frontend.AppUserRequest;
import com.example.oauth.service.AppUserService;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;
	
	@PostMapping
	public ResponseEntity<AppUserResponse> createUser(@Valid @RequestBody AppUserRequest request) {
		AppUserResponse user = this.appUserService.createUser(request);
		
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(user.getId())
							.toUri();
		
		return ResponseEntity.created(location).body(user);
	}
	
}
