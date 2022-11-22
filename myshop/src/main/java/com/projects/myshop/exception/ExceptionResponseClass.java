package com.projects.myshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projects.myshop.config.ResponseMessageClass;
import com.projects.myshop.exception.MainExceptionClass.EmailAlreadyExits;
import com.projects.myshop.exception.MainExceptionClass.EmailNotRegistered;
import com.projects.myshop.exception.MainExceptionClass.ProductCompanyAlreadyExits;
import com.projects.myshop.exception.MainExceptionClass.ProductTypesAlreadyExits;

@RestControllerAdvice
@ResponseStatus
public class ExceptionResponseClass extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmailAlreadyExits.class)
	public ResponseEntity<ResponseMessageClass<Object>> emailAlreadyExits(EmailAlreadyExits exception) {
		ResponseMessageClass<Object> re = new ResponseMessageClass<>(exception.getMessage(),
				HttpStatus.ALREADY_REPORTED, "warning");
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(re);
	}

	@ExceptionHandler(EmailNotRegistered.class)
	public ResponseEntity<ResponseMessageClass<Object>> emailNotRegistered(EmailNotRegistered emailNotRegistered) {
		ResponseMessageClass<Object> re = new ResponseMessageClass<>(emailNotRegistered.getMessage(),
				HttpStatus.NOT_FOUND, "warning");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re);
	}

	@ExceptionHandler(ProductTypesAlreadyExits.class)
	public ResponseEntity<ResponseMessageClass<Object>> productTypesAlreadyExits(
			ProductTypesAlreadyExits alreadyExits) {
		ResponseMessageClass<Object> re = new ResponseMessageClass<>(alreadyExits.getMessage(),
				HttpStatus.ALREADY_REPORTED, "warning");
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(re);
	}

	@ExceptionHandler(ProductCompanyAlreadyExits.class)
	public ResponseEntity<ResponseMessageClass<Object>> productCompanyAlreadyExits(
			ProductCompanyAlreadyExits alreadyExits) {
		ResponseMessageClass<Object> re = new ResponseMessageClass<>(alreadyExits.getMessage(),
				HttpStatus.ALREADY_REPORTED, "warning");
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(re);
	}
}
