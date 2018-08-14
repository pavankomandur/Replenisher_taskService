package com.personal.replenish.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.personal.replenish.entity.Role;
import com.personal.replenish.entity.User;
import com.personal.replenish.exception.DuplicateCreationException;
import com.personal.replenish.exception.UserNotAnAdminException;
import com.personal.replenish.repository.UserRepository;
import com.personal.replenish.util.ApplicationUtilities;

@Service
public class UserService {

	static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ApplicationUtilities utilities;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	   *This Method is used to create user
	   *
	   *@param user : user Object
	   * 
	   * 
	   */
	public User createUser(User user) {
		log.info("Creating user");
		Role role = user.getRole();
		
		if(role != null)
			utilities.validateRole(role);
		
		try {
			return userRepository.save(user);
		}
		catch(DataIntegrityViolationException ce) {
			throw new DuplicateCreationException("User with username " + user.getName() +" already exists");
		}
		
	}
	
	/**
	   *This Method is used to update the user
	   *
	   *@param user : user Object
	   * 
	   * 
	   */
	public User updateUser(String username, User user) {
		
		if(!userRepository.existsByName(username))
			throw new UsernameNotFoundException("User " + username + " doesn't exist");
			
		User fromDatabase = userRepository.findByName(username).get();
		user.setId(fromDatabase.getId());
		
		if(user.getName() == null)
			user.setName(fromDatabase.getName());
		
		if(user.getRole() == null)
			user.setRole(fromDatabase.getRole());
		else
			utilities.validateRole(user.getRole());
		
		if(user.getPassword() == null)
			user.setPassword(fromDatabase.getPassword());
		
		return userRepository.
				save(user);
		
	}
	
	public User getUser(String username) {
		
		if(!userRepository.existsByName(username))
			throw new UsernameNotFoundException("User " + username + " doesn't exist");
		
		User user = userRepository.findByName(username).get();
		return user;
		
	}
	
	public User createAdmin(User user) {
		
		log.info("Creating admin");

		if(!user.getRole().getRole().equals("ADMIN"))
			throw new UserNotAnAdminException("User roles contain roles other than ADMIN");
		
		try {
			return userRepository.save(user);
		}
		catch(DataIntegrityViolationException ce) {
			throw new DuplicateCreationException("User with username " + user.getName() +" already exists");
		}
	}
	
//	public void userExists(String username) {
//		if(!userRepository.existsByName(username))
//			throw new UserNotFoundException("User with username " + username + " not found !");
//	}
	
}
