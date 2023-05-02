package com.exam.portal.helper;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("user not there try another");
	}
	public UserNotFoundException(String msg) {super(msg);}
}
