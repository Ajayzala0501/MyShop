package com.projects.myshop.config;

import org.springframework.http.HttpStatus;

public class ResponseMessageClass<T> {
	
	public T result;
	
	public HttpStatus status;

	public ResponseMessageClass(T result, HttpStatus status) {
		super();
		this.result = result;
		this.status = status;
		
	}
}
