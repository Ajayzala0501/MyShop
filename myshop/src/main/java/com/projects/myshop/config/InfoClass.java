package com.projects.myshop.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.projects.myshop.enitity.Registration;

public class InfoClass {
	
	public static HttpSession ses = null;
	
	public static Registration getCurrentUser(HttpServletRequest request) {
		CustomeUserDetailsImpl userDetails = (CustomeUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Registration reg = userDetails.getRegistration();
		ses = request.getSession();
		ses.setAttribute("CurrentUser", reg);
		return (Registration)ses.getAttribute("CurrentUser");
	}

}
