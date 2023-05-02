package com.exam.portal.helper;

public class UserFoundException extends Exception {
	public UserFoundException() {
		super("user with this name already there");
	}
	public UserFoundException(String msg) {super(msg);}
}
