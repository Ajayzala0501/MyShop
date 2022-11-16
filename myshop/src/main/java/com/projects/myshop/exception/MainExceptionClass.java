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
	
	public class EmailNotRegistered extends Exception{
		
		public EmailNotRegistered() {
			// TODO Auto-generated constructor stub
		}
		
		public EmailNotRegistered(String  msg){
			super(msg);
		}
	}
}
