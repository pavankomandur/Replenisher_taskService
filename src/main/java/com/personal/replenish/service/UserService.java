package com.personal.replenish.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.replenish.entity.User;
import com.personal.replenish.entity.User_Authority;
import com.personal.replenish.exception.DuplicateCreationException;
import com.personal.replenish.model.UserTO;
import com.personal.replenish.repository.UserRepository;
import com.personal.replenish.repository.UserRoleRepository;

@Service
public class UserService {

	static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	@Transactional(rollbackFor = DataIntegrityViolationException.class)
	public User createUser(UserTO userTO) {
		log.info("Creating user");
		User user=new User();
		user.setEmail(userTO.getEmail());
		user.setEnabled(true);
		user.setFirstname(userTO.getFirstname());
		user.setLastname(userTO.getLastname());
		user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
		user.setPassword(passwordEncoder.encode(userTO.getPassword()));
		user.setUsername(userTO.getUsername());

		try {
			User newUser=userRepository.save(user);
			
			User_Authority userAuth=new User_Authority();
			userAuth.setUser_id(String.valueOf(newUser.getId()));
			userAuth.setAuthority_id(userTO.getRoleId());
			userRoleRepository.save(userAuth);
			return newUser;
		}
		catch(DataIntegrityViolationException ce) {
			
			throw new DuplicateCreationException("User with username " + user.getUsername() +" already exists");
		}
		
	}
	
	/**
	   *This Method is used to update the user
	   *
	   *@param user : user Object
	   * 
	   * 
	   */
	public User updateUser(UserTO userTO) {
		log.info("Updating user");
		User user=new User();
		user.setId(userTO.getUserid());
		user.setEmail(userTO.getEmail());
		user.setEnabled(true);
		user.setFirstname(userTO.getFirstname());
		user.setLastname(userTO.getLastname());
		user.setLastPasswordResetDate(new Timestamp(new Date().getTime()));
		user.setPassword(passwordEncoder.encode(userTO.getPassword()));
		user.setUsername(userTO.getUsername());

		try {
			return userRepository.save(user);
		}
		catch(Exception ce) {
			log.error(ce.getMessage());
			return null;
			//TODO : Throw Proper  Exception
			
		}
		
		
	}
	
	public User getUser(String username) {
		
		if(userRepository.findByUsername(username)==null)
			throw new UsernameNotFoundException("User " + username + " doesn't exist");
		else
			return userRepository.findByUsername(username);
		
	}
	
	
	
}
