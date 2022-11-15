package com.projects.myshop.controller;

import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/registerPageLink")
	public String register() {
		return "register";
	}
	
	@GetMapping("/emptyPage")
	public String emptypage() {
		return "base_page";
	}
	
	@GetMapping("/forgotPassword")
		public String forgotPassword() {
		return "forgot_password";
	}
	
}
