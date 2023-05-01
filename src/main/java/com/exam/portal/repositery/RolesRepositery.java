package com.exam.portal.repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.model.Roles;

public interface RolesRepositery extends JpaRepository<Roles, Long> {

}
