package com.projects.myshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfiguration  extends WebSecurityConfigurerAdapter{

	
	@Bean
	public UserDetailsService customeUserDetailsImpl() {
		return new CustomeUserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customeUserDetailsImpl());
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
	return authenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
	}
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
		       .ignoring()
		       .antMatchers("/resources/**","/templates/**", "/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**", "/email_templates/**","/font-awesome/**","/LESS/**","/locales/**","/pdf/**");
	    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stubhttp.authorizeRequests()
		http.authorizeRequests().antMatchers("/Product/**","/ViewProduct/**").hasAuthority("USER").antMatchers("/emptyPage","/registerPageLink","/forgotPassword","/verifyToken","/verifyTokenForRegistration","/Registration/**","/login").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/")  
	      .defaultSuccessUrl("/",true)
	      .failureUrl("/login?error=true").and().csrf().disable();
	}	
}
