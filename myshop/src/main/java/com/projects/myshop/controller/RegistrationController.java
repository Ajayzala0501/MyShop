package com.projects.myshop.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.projects.myshop.Service.RegistrationService;
import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.exception.MainExceptionClass;
import com.projects.myshop.model.LoginModel;
import com.projects.myshop.model.RegistrationModel;

@RestController
public class RegistrationController {

	@Autowired 
	MainExceptionClass mainExceptionClass;
	
	@Autowired
	RegistrationService registrationService;
	

	
	@PostMapping("/registration")
		public ResponseEntity<ResponseMessageClass<Object>> addRegistration(@RequestBody RegistrationModel model) throws com.projects.myshop.exception.MainExceptionClass.EmailAlreadyExits{	
		Optional<Registration> checkEmail = registrationService.checkEmailAlreadyExits(model.getEmail());
	 	if(checkEmail.isPresent()){
	 		throw mainExceptionClass.new EmailAlreadyExits("Email Already Exits");
	 	}
		Registration re = registrationService.addRegistration(model);
	 	if(re != null) {	
	 		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>("Registration Successfull!!!", HttpStatus.OK,"success"));
	 	}
	 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Registration Not Success!!!", HttpStatus.BAD_REQUEST,"warning"));
	}

	/*
	 * @PostMapping("/login") public ResponseEntity<ResponseMessageClass<Object>>
	 * checkLogin(@RequestBody LoginModel loginModel, HttpServletRequest request){
	 * Optional<Registration> loginUser =
	 * registrationService.checkLogin(loginModel); if(!loginUser.isEmpty()
	 * &&loginModel.getEmail().equals(loginUser.get().getEmail()) &&
	 * loginModel.getPassword().equals(loginUser.get().getPassword())) { HttpSession
	 * ses = request.getSession(); ses.setAttribute("CurrentUser", loginUser.get());
	 * return ResponseEntity.status(HttpStatus.OK).body(new
	 * ResponseMessageClass<Object>("Login Successfull", HttpStatus.OK,"success"));
	 * }else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	 * ResponseMessageClass<Object>("Bad User", HttpStatus.BAD_REQUEST,"error")); }
	 * }
	 */
}
