package com.projects.myshop.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.projects.myshop.enitity.Registration;
import com.projects.myshop.repository.RegistrationRepository;

public class CustomeUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	RegistrationRepository registrationRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
 Optional<Registration>	reg =registrationRepository.findByUsername(username);
if(reg.isPresent()) {
	throw new UsernameNotFoundException("User Not Found");
}		
 return new CustomeUserDetailsImpl(reg.get());
	}

}
