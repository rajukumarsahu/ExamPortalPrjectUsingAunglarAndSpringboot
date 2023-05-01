package com.exam.portal.jwt;

import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


public JwtAuthenticationEntryPoint(){

}
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		 
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized:server");
	}

	 

}
