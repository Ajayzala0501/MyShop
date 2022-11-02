package com.projects.myshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.exception.MainExceptionClass.EmailAlreadyExits;

@RestControllerAdvice
@ResponseStatus
public class ExceptionResponseClass  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EmailAlreadyExits.class)
	public ResponseEntity<ResponseMessageClass<Object>> emailAlreadyExits(EmailAlreadyExits exception){
		ResponseMessageClass re = new ResponseMessageClass(exception.getMessage(),HttpStatus.ALREADY_REPORTED);
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(re);
	}
	
}
