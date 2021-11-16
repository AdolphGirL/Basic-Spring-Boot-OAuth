package com.example.oauth.domain.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.example.oauth.domain.bo.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	
	public Optional<AppUser> findByEmailAddress(String email);
	
}
