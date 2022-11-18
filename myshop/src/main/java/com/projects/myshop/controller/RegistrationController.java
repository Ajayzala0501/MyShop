package com.projects.myshop.controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.myshop.Service.RegistrationService;
import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.config.Token;
import com.projects.myshop.emailservice.EmailDetails;
import com.projects.myshop.emailservice.EmailService;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.exception.MainExceptionClass;
import com.projects.myshop.model.LoginModel;
import com.projects.myshop.model.RegistrationModel;
import com.projects.myshop.model.ResetPasswordModel;

@RestController
@RequestMapping("/Registration")
public class RegistrationController {

	@Autowired 
	MainExceptionClass mainExceptionClass;
	
	@Autowired
	RegistrationService registrationService;

	
	@PostMapping("/registrationVerification")
		public ResponseEntity<ResponseMessageClass<Object>> addRegistration(@RequestBody RegistrationModel model, HttpServletRequest  request) throws com.projects.myshop.exception.MainExceptionClass.EmailAlreadyExits{	
		Optional<Registration> checkEmail = registrationService.checkEmailAlreadyExits(model.getEmail(),model.getUsername());
	 	if(checkEmail.isPresent()){
	 		throw mainExceptionClass.new EmailAlreadyExits("Email Or Username Already Exits");
	 	}	 	
		//Registration re = registrationService.addRegistration(model);
		String res =  registrationService.saveTemporaryDetailsAndTokenGeneration(model,request);
	 	if(res != null) {	
	 		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>("Registration Successfull!!!", HttpStatus.OK,"success"));
	 	}
	 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Registration Not Success!!!", HttpStatus.BAD_REQUEST,"warning"));
	}

	@PostMapping("/forgotPasswordProcess")
	public ResponseEntity<ResponseMessageClass<Object>> forgotPasswordProcess(@RequestBody RegistrationModel model, HttpServletRequest request) throws com.projects.myshop.exception.MainExceptionClass.EmailNotRegistered{
		Optional<Registration> checkEmail = registrationService.findByemail(model.getEmail());
		if(checkEmail.isPresent()) {
			
			String res =  registrationService.ForgotPasswordProcessImpl(checkEmail.get(),request);
			if(res != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(res,HttpStatus.OK,"success"));		
			}else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessageClass<Object>(res,HttpStatus.NOT_MODIFIED,"warning"));	
			}
		}else {
			throw mainExceptionClass.new EmailNotRegistered("Please enter registered email!!");
		}
	}
	public boolean verifyToken(String token) {
		return registrationService.verifyToken(token);
	}
	
	@PostMapping("/resetpassword")
	public ResponseEntity<ResponseMessageClass<Object>> resetPassword(@RequestBody ResetPasswordModel model){
		if(registrationService.resetPassword(model)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>("Resetpassword Successfull!!!", HttpStatus.OK,"success"));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>("Password Not Reset", HttpStatus.NOT_MODIFIED,"warning"));
		}
		
	}
	public boolean verifyTokenForRegistration(String token, HttpServletRequest request) {
		
		return registrationService.verifyTokenForRegistration(token, request);
	}
}
