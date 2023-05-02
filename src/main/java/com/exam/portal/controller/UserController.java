package com.exam.portal.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.helper.UserFoundException;
import com.exam.portal.model.Roles;
import com.exam.portal.model.UserModel;
import com.exam.portal.model.UserRoles;
import com.exam.portal.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userservice;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/save")
	public UserModel createuser(@RequestBody UserModel user) throws Exception {
	 
		Set<UserRoles> rolesset=new HashSet<UserRoles>();
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Roles roles=new Roles();
		roles.setRoleid(44L);
		roles.setRolename("Admin"); 
		
		UserRoles userroles=new UserRoles();
		userroles.setRole(roles);
		userroles.setUser(user);
		
		rolesset.add(userroles);
		
		return this.userservice.createuser(user, rolesset);	
	}
	@GetMapping("/{username}")
	public UserModel getDetails(@PathVariable("username")String username) {
		
		return this.userservice.getUser(username); 
	}
	
	@DeleteMapping("/{userid}")
	public void deleteuser(@PathVariable("userid") Long userid) {
		this.userservice.deleteuser(userid);
	}
	@GetMapping("/UserDetails")
	public List<UserModel> getallusers() {
		return this.userservice.getallUser();
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?>exceptionhandler(UserFoundException ex){
		 //String errorMessage = "User Already there Try Another";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
	}
}
