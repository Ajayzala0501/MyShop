package com.projects.myshop.model;

public class ResetPasswordModel {

	private String token;
	
	private String password;

	public ResetPasswordModel(String token, String password) {
		super();
		this.token = token;
		this.password = password;
	}

	public ResetPasswordModel() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
