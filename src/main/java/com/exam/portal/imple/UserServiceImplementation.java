package com.exam.portal.imple;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.model.UserModel;
import com.exam.portal.model.UserRoles;
import com.exam.portal.repositery.RolesRepositery;
import com.exam.portal.repositery.UserRepositery;
import com.exam.portal.service.UserService;
 

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepositery userrepositery;
	
	@Autowired 
	private RolesRepositery rolesRepositery;
	
	
	@Override
	public UserModel createuser(UserModel user, Set<UserRoles> userRoles) throws Exception {
	 
		UserModel local=this.userrepositery.findByusername(user.getUsername());
	 
		if(local!=null) {
			System.out.println("User is already present");
			throw new Exception("Already user There");
		}
		else {
			for(UserRoles ur:userRoles) {
				rolesRepositery.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local=this.userrepositery.save(user);
		}
		
		return local;
	}

//getting user by user name
	@Override
	public UserModel getUser(String username) {
	 
		return this.userrepositery.findByusername(username);
	}
//delete by id
	@Override
	public void deleteuser(Long userId) {
		
		  this.userrepositery.deleteById(userId);
	}
//get all user  

@Override
public List<UserModel> getallUser() {
	// TODO Auto-generated method stub
	return this.userrepositery.findAll();
}
	 

}
