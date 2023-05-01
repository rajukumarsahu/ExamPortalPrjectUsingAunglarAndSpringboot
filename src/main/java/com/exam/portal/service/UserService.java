package com.exam.portal.service;

import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.stereotype.Service;

import com.exam.portal.model.UserModel;
import com.exam.portal.model.UserRoles;
 
 
public interface UserService {
	
	//creating user
	public UserModel createuser(UserModel user,Set<UserRoles> userRoles) throws Exception ;
//get user by user name
	public UserModel getUser(String username);
	
	//Delete by user
	public void deleteuser(Long userid); 
	
	//get all user
	 
	public List<UserModel> getallUser();
}
