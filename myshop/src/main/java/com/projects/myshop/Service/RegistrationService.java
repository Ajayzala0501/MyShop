package com.projects.myshop.Service;

import java.util.Optional;

import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.LoginModel;
import com.projects.myshop.model.RegistrationModel;

public interface RegistrationService {

	Registration addRegistration(RegistrationModel model);

	Optional<Registration> checkEmailAlreadyExits(String email);

	Optional<Registration> checkLogin(LoginModel loginModel);

}
