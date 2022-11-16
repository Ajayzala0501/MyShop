package com.projects.myshop.config;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Token {

	String token;
	
	Date expriteToken;
	
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

	public static Token generateNewToken() {
		Calendar date = Calendar.getInstance();
		long timeInSecs = date.getTimeInMillis();
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return new Token(base64Encoder.encodeToString(randomBytes),new Date(timeInSecs + (10 * 60 * 1000))) ;
	}

	public Token(String token, Date expriteToken) {
		super();
		this.token = token;
		this.expriteToken = expriteToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpriteToken() {
		return expriteToken;
	}

	public void setExpriteToken(Date expriteToken) {
		this.expriteToken = expriteToken;
	}
	
	
}
