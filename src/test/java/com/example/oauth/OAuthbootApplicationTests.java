package com.example.oauth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class OAuthbootApplicationTests {
	
	@Test
	void contextLoads() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("QQ"));
		
		System.out.println(passwordEncoder.matches("QQ", "$2a$10$YIYh8HE3EQLL3rSezpFIq.ilh9aTWNo4xfYy/tbrgbaDQqOVde3yy"));
		
	}

}
