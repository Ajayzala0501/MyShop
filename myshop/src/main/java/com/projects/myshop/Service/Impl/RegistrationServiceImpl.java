package com.projects.myshop.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projects.myshop.Service.RegistrationService;
import com.projects.myshop.enitity.Registration;
import com.projects.myshop.model.LoginModel;
import com.projects.myshop.model.RegistrationModel;
import com.projects.myshop.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Registration addRegistration(RegistrationModel model) {
		// TODO Auto-generated method stub
		Registration re = new Registration();
		re.setUsername(model.getUsername());
		re.setEmail(model.getEmail());
		re.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
		return registrationRepository.save(re);
	}

	@Override
	public Optional<Registration> checkEmailAlreadyExits(String email,String username) {
		return registrationRepository.findByEmailAndUsername(email,username);
	}

	@Override
	public Optional<Registration> checkLogin(LoginModel loginModel){
		// TODO Auto-generated method stub
		return registrationRepository.findByEmailAndPassword(loginModel.getEmail(), loginModel.getPassword());
	}

}
