package com.projects.myshop.controller;

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

@RestController
public class RegistrationController {

	@Autowired 
	MainExceptionClass mainExceptionClass;
	
	@Autowired
	RegistrationService registrationService;

	@Autowired
	EmailService emailService;
	
	@PostMapping("/registration")
		public ResponseEntity<ResponseMessageClass<Object>> addRegistration(@RequestBody RegistrationModel model) throws com.projects.myshop.exception.MainExceptionClass.EmailAlreadyExits{	
		Optional<Registration> checkEmail = registrationService.checkEmailAlreadyExits(model.getEmail(),model.getUsername());
	 	if(checkEmail.isPresent()){
	 		throw mainExceptionClass.new EmailAlreadyExits("Email Or Username Already Exits");
	 	}
		Registration re = registrationService.addRegistration(model);
	 	if(re != null) {	
	 		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>("Registration Successfull!!!", HttpStatus.OK,"success"));
	 	}
	 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageClass<Object>("Registration Not Success!!!", HttpStatus.BAD_REQUEST,"warning"));
	}

	@PostMapping("/forgotPasswordProcess")
	public ResponseEntity<ResponseMessageClass<Object>> forgotPasswordProcess(@RequestBody RegistrationModel model, HttpServletRequest request) throws com.projects.myshop.exception.MainExceptionClass.EmailNotRegistered{
		Optional<Registration> checkEmail = registrationService.findByemail(model.getEmail());
		if(checkEmail.isPresent()) {
			Token t = Token.generateNewToken();
				checkEmail.get().setToken(t.getToken());
				checkEmail.get().setTokenExpirationTime(t.getExpriteToken());
				registrationService.saveData(checkEmail.get());
				
	//			EmailDetails details = new EmailDetails(model.getEmail(),)
			String msgBody = mailMessage(applicationURL(t, request));
			
		String res = emailService.sendMailWithAttachment(new EmailDetails(model.getEmail(), msgBody, "Verify Your E-mail Address"));
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageClass<Object>(res,HttpStatus.OK,"success"));	
		}else {
			throw mainExceptionClass.new EmailNotRegistered("Please enter registered email!!");
		}
	}
	
	public String applicationURL(Token token, HttpServletRequest request) {
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/verifyToken?Token="+token.getToken();
	}
	public String mailMessage(String url) {
		String content = "";    
		try {        
		        StringBuilder bldr = new StringBuilder();
		        String str="";
		        BufferedReader in = new BufferedReader(new FileReader("../myshop/src/main/resources/static/email_templates/action.html"));
		        while((str = in.readLine())!=null)
		              bldr.append(str);
		        in.close();
		        content = bldr.toString();		        
		        content = content.replace("{{Header}}", "Email Verification").replace("{{Content}}", "You're almost ready to get started. Please click on the button below to verify your email address and enjoy exclusive cleaning services with us!").replace("{{url}}", url).replace("{{Button-Name}}", "VERIFY YOUR EMAIL");
		        in.close();
		    } catch (IOException e) {
		    }
		return content;
	}
	public boolean verifyToken(String token) {
		return registrationService.verifyToken(token);
	}
}
