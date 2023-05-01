 package com.exam.portal.controller;

import java.awt.print.Printable;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.imple.UserDetailsServiceImpl;
import com.exam.portal.jwt.JwtUtil;
import com.exam.portal.model.JwtRequest;
import com.exam.portal.model.JwtResponse;
import com.exam.portal.model.UserModel;

@RestController
@CrossOrigin("*")
 
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userdetailsService;
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	@Autowired
	private JwtUtil jwtUtil;
	//generate token
	@PostMapping("/generatetoken")
	public ResponseEntity<?>generatetoken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		
		try {
			//this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			authentication(jwtRequest.getUsername(),jwtRequest.getPassword());
			
			
		}catch (UsernameNotFoundException e) {
			 e.printStackTrace();
			 throw new Exception("user not found");
		}
		
		UserDetails  userdetails= this.detailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtil.generateToken(userdetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	
	
	
	
	private void authentication(String username,String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}
		catch (DisabledException e) {
			// TODO: handle exception
		throw new Exception("User Disabled "+e.getMessage());
		}
		catch (BadCredentialsException e) {
			 throw new Exception("invalid crediantial"+e.getMessage());
		}
		
	}
	//Return the current user
	@GetMapping("/get-currentuser")
	public UserModel getUser(Principal principal) {
		return ((UserModel)this.userdetailsService.loadUserByUsername(principal.getName()));
	}

}
