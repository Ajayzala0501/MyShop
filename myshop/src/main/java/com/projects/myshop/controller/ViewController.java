package com.projects.myshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	
	@Autowired
	RegistrationController controller;
	
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
	
	@GetMapping("/verifyToken")
	public String verifyToken(@RequestParam("Token") String token,Model model) {
		if(controller.verifyToken(token)) {
			model.addAttribute("token", token);
			return"confirm_password";
		}
		model.addAttribute("expire", true);
		return "forgot_password";
	}
	
	@GetMapping("/verifyTokenForRegistration")
	public String verifyTokenForRegistration(@RequestParam("Token") String token, Model model,HttpServletRequest request) {
		if(controller.verifyTokenForRegistration(token, request)) {
			return "login";
		}
		model.addAttribute("expire",true);
		return "register";
	}
	


	
}
