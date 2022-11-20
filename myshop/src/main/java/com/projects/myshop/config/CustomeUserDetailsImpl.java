package com.projects.myshop.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projects.myshop.enitity.Registration;

import net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy.Simple;

public class CustomeUserDetailsImpl implements UserDetails{

	Registration registration;
	
	
	public CustomeUserDetailsImpl() {
		super();
	}

	public CustomeUserDetailsImpl(Registration  registration){
		this.registration = registration;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(registration.getRole());
		return List.of(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return registration.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return registration.getUsername();
	}

	public Registration getRegistration() {
		return registration;
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
