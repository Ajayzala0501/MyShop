package com.projects.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "signin";
	}
	
	@GetMapping("/registerPageLink")
	public String register() {
		return "register";
	}
	
	@GetMapping("/emptyPage")
	public String emptypage() {
		return "base_page";
	}
}
