package com.example.oauth.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.oauth.domain.backend.AppUserResponse;
import com.example.oauth.domain.bo.AppUser;
import com.example.oauth.domain.bo.AppUserAuthority;
import com.example.oauth.domain.frontend.AppUserRequest;
import com.example.oauth.service.AppUserService;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;
	
	@GetMapping
	public ResponseEntity<List<AppUserResponse>> getUsers() {
		List<AppUserResponse> users = this.appUserService.getUserResponses();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppUserResponse> getUser(@PathVariable("id") long id) {
		AppUserResponse user = this.appUserService.getUserResponseById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<AppUserResponse> createUser(@Valid @RequestBody AppUserRequest request) {
		AppUserResponse user = this.appUserService.createUser(request);
		
//		回應標頭加上URI
		URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(user.getId())
							.toUri();
		
		return ResponseEntity.created(location).body(user);
	}
	
	
	public static void main(String[] args) {
		AppUser appUser = new AppUser();
		appUser.setAuthorities(Arrays.asList(AppUserAuthority.ADMIN));
	}
	
}
