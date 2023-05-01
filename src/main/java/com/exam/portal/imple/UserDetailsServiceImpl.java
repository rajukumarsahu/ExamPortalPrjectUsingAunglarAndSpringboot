package com.exam.portal.imple;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.portal.model.UserModel;
import com.exam.portal.repositery.UserRepositery;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepositery userRepositery;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
	UserModel user=	this.userRepositery.findByusername(username);
		if(user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("not found exception");
		}
		return user;
	}

	 

}
