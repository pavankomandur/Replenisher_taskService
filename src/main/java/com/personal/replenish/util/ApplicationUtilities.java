package com.personal.replenish.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.replenish.entity.Role;
import com.personal.replenish.exception.InvalidRoleException;
import com.personal.replenish.repository.RoleRepository;

@Service
public class ApplicationUtilities {
	@Autowired
	private RoleRepository roleRepository;
	
	
	/**
	   *This Method is used to validate the Role when user is created 
	   *
	   *@param role : role name
	   * 
	   * 
	   */
	
	
	public void validateRole(Role role) {
		
		if(!roleRepository.existsById(role.getRole()))
			throw new InvalidRoleException("Role " + role.getRole() + " doesn't exist!");

	}
	
}
