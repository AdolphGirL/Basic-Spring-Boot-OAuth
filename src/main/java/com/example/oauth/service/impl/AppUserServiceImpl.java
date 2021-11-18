package com.example.oauth.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.oauth.converter.AppUserConverter;
import com.example.oauth.domain.backend.AppUserResponse;
import com.example.oauth.domain.bo.AppUser;
import com.example.oauth.domain.frontend.AppUserRequest;
import com.example.oauth.domain.repository.AppUserRepository;
import com.example.oauth.exception.NotFoundException;
import com.example.oauth.exception.UnprocessableEntityException;
import com.example.oauth.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserServiceImpl.class);
	
	private AppUserRepository appUserRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	public AppUserServiceImpl(AppUserRepository repository) {
		this.appUserRepository = repository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@Override
	public AppUserResponse createUser(AppUserRequest request) {
		Optional<AppUser> existingUser = this.appUserRepository.findByEmailAddress(request.getEmailAddress());
		if (existingUser.isPresent()) {
			throw new UnprocessableEntityException(" This email address has been used. ");
		}
		
		AppUser user = AppUserConverter.toAppUser(request);
		user.setPassword(this.passwordEncoder.encode(request.getPassword()));
		
		this.appUserRepository.save(user);
		
		return AppUserConverter.toAppUserResponse(user);
	}
	
	@Override
	public List<AppUserResponse> getUserResponses() {
		List<AppUser> users = (List<AppUser>) this.appUserRepository.findAll();
		return AppUserConverter.toAppUserResponses(users);
	}

	@Override
	public AppUserResponse getUserResponseById(long id) {
		AppUser user = this.appUserRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Can't find user."));
		
		return AppUserConverter.toAppUserResponse(user);
	}

	@Override
	public AppUser getUserByEmail(String email) {
		return this.appUserRepository.findByEmailAddress(email)
					.orElseThrow(() -> new NotFoundException("Can't find user."));
	}
	
	
	
}
