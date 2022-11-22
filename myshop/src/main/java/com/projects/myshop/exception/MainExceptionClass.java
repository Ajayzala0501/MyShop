package com.projects.myshop.exception;

import org.springframework.stereotype.Component;

@Component
public class MainExceptionClass {

	public class EmailAlreadyExits extends Exception{
		
		public EmailAlreadyExits() {
			// TODO Auto-generated constructor stub
			super();
		}
		
		public EmailAlreadyExits(String  msg){
			super(msg);
		}
	}
	
	public class EmailNotRegistered extends Exception{
		
		public EmailNotRegistered() {
			// TODO Auto-generated constructor stub
			super();
		}
		
		public EmailNotRegistered(String  msg){
			super(msg);
		}
	}
	
	public class ProductTypesAlreadyExits extends Exception{

		public ProductTypesAlreadyExits() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ProductTypesAlreadyExits(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
	}

	public class ProductCompanyAlreadyExits extends Exception{

		public ProductCompanyAlreadyExits() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ProductCompanyAlreadyExits(String message) {
			super(message);
			// TODO Auto-generated constructor stub
		}
	}
}


