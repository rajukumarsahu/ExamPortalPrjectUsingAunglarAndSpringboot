package com.exam.portal;

import java.util.HashSet;
import java.util.Set;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.portal.model.Roles;
import com.exam.portal.model.UserModel;
import com.exam.portal.model.UserRoles;
import com.exam.portal.service.UserService;

@SpringBootApplication
public class WebExamPortalApplication   {
	 
	public static void main(String[] args) {
		SpringApplication.run(WebExamPortalApplication.class, args);
	}

		
	

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("Starting code");
//		UserModel user=new UserModel();
//		
//		user.setFirstname("raju");
//		user.setLastname("Kumar");
//		user.setUsername("sahu");
//		user.setPassword("123");
//		user.setEmail("raju@.com");
//		user.setPhone("45612378");
	//user.setProfile("degault.png");
	
//		
//		Roles roles1 =new Roles();
//		roles1.setRoleid(44L);
//		roles1.setRolename("Admin");
//		
//		Set<UserRoles> userroleset=new HashSet<UserRoles>();
//		UserRoles userrole=new UserRoles();
//		userrole.setRole(roles1);
//		userrole.setUser(user);
//		
//		userroleset.add(userrole);
//		 
//		
//		UserModel user1=this.userservice.createuser(user, userroleset);
//		System.out.println(user1.getUsername()); 

	
	
}
