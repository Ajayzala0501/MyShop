package com.projects.myshop.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.projects.myshop.enitity.Registration;

public class InfoClass {
	
	public static HttpSession ses = null;
	
	public static Registration getCurrentUser(HttpServletRequest request) {
		ses = request.getSession();
		return (Registration)ses.getAttribute("CurrentUser");
	}

}
