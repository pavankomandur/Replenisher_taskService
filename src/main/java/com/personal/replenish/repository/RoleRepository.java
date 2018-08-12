package com.personal.replenish.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.replenish.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
	
}
