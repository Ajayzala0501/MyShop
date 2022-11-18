package com.projects.myshop.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

import com.projects.myshop.enitity.Registration;

public class InfoClass {
	
	public static HttpSession ses = null;
	
	public static Registration getCurrentUser(HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass();
		Registration reg = (Registration)principal;
		ses = request.getSession();
		return (Registration)ses.getAttribute("CurrentUser");
	}

}
