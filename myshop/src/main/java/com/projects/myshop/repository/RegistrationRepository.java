package com.projects.myshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myshop.enitity.Registration;

@Repository
public interface RegistrationRepository  extends JpaRepository<Registration, Long>{

	Optional<Registration> findByEmailAndUsername(String email,String username);
 	
	Optional<Registration> findByEmailAndPassword(String email, String password);
	
	Optional<Registration> findByUsername(String username);
	
	Optional<Registration> findByEmail(String email);
	
	Optional<Registration> findByToken(String token);
 	
}
