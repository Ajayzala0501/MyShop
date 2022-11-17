package com.projects.myshop.Service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.LoginModel;
import com.projects.myshop.model.RegistrationModel;
import com.projects.myshop.model.ResetPasswordModel;

public interface RegistrationService {

	Registration addRegistration(RegistrationModel model);

	Optional<Registration> checkEmailAlreadyExits(String email,String username);

	Optional<Registration> checkLogin(LoginModel loginModel);

	Optional<Registration> findByemail(String email);
	
	Registration saveData(Registration registration);
	 
	Boolean verifyToken(String token);
	 
	Boolean resetPassword(ResetPasswordModel model);
	
	String ForgotPasswordProcessImpl(Registration checkEmail, HttpServletRequest request);
	
	String saveTemporaryDetailsAndTokenGeneration(RegistrationModel model,HttpServletRequest request);
	
	Boolean verifyTokenForRegistration(String token, HttpServletRequest request);
}
