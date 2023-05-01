package com.exam.portal.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.model.UserModel;

public interface UserRepositery extends JpaRepository<UserModel, Long>{

	public UserModel findByusername(String username);

}
