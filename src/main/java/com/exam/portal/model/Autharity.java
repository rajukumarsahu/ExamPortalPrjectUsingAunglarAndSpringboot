package com.exam.portal.model;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.security.core.GrantedAuthority;

public class Autharity implements GrantedAuthority{
		
	private String autharity;
	
	
	public Autharity(String autharity) {
		 
		this.autharity = autharity;
	}


	@Override
	public String getAuthority() {
		 
		return this.autharity;
	}
	
	 

}
