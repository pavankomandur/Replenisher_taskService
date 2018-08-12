package com.personal.replenish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.replenish.entity.Role;
import com.personal.replenish.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
	
}
