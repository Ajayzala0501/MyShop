package com.projects.myshop.config;

import org.springframework.http.HttpStatus;

public class ResponseMessageClass<T> {
	
	private T result;
	
	private HttpStatus status;
	
	private String msgType;

	

	public ResponseMessageClass(T result, HttpStatus status, String msgType) {
		super();
		this.result = result;
		this.status = status;
		this.msgType = msgType;
	}

	public ResponseMessageClass() {
		super();
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
