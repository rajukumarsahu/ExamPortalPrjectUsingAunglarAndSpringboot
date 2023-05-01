package com.exam.portal.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Roles {
	@Id
	private Long roleid;
	private String rolename;
	
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "roles")
	Set<UserRoles>userRoles=new HashSet<>();
	
	
	
	public Roles(){
		
	}



	public Long getRoleid() {
		return roleid;
	}



	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}



	public String getRolename() {
		return rolename;
	}



	public void setRolename(String rolename) {
		this.rolename = rolename;
	}



	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}



	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}
	
	 
	
	

}
