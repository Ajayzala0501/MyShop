package com.projects.myshop.exception;

import org.springframework.stereotype.Component;

@Component
public class MainExceptionClass {

	public class EmailAlreadyExits extends Exception{
		
		public EmailAlreadyExits() {
			// TODO Auto-generated constructor stub
		}
		
		public EmailAlreadyExits(String  msg){
			super(msg);
		}
	}
	
}
