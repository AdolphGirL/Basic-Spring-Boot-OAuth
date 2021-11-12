package com.example.oauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@GetMapping(value = "/")
	public String index(Model modal) {
		return "index";
	}
	
	@GetMapping(value = "/test")
	public String test(Model modal) {
		return "test";
	}
}
