package com.personal.replenish.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personal.replenish.entity.User;
import com.personal.replenish.service.UserService;

@CrossOrigin
@RequestMapping("replenisher/authorized/users")
@RestController
public class UserController {

	static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	/**
	   *This Method is used to Create a User: Only Role with Admin can Create a user.
	   *
	   *@End Point : http://localhost:9090/replenisher/authorized/users/create
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *@Request : user : user object as json
	   *@Method : POST     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize can be commented for PostMan Testing
	   */
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	public User createUser(@RequestBody User user) {
		log.debug("Inside Create User ");
		return userService.createUser(user);
	}
	
	/**
	   *This Method is used to Update a User: Only Role with Admin can Update a user.
	   *
	   *@End Point : http://localhost:9090/replenisher/authorized/users/{username}
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *@Request : username : username from path variable
	   *@Method : PUT     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize can be commented for PostMan Testing
	   */
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/{username}")
	public User updateUser(@RequestBody User user, @PathVariable("username") String username) {
		log.debug("Inside Update User ");
		return userService.updateUser(username, user);
	}
	
	/**
	   *This Method is used to retrieve all Users: Only Role with Admin can get all Users.
	   *
	   *@End Point : http://localhost:9090/replenisher/authorized/users/allUsers
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *   *@Method : GET     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize can be commented for PostMan Testing
	   */
	
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/allUsers")
	public List<User> getAllUsers() {
		log.debug("Inside get All Users");
		return userService.getAllUsers();
	}
	
	
	/**
	   *This Method is used to retrieve particular user details: all the Roles can access this feature.
	   *
	   *@End Point : http://localhost:9090/replenisher/authorized/users/{username}
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *@Request : username : username from path variable
	   *@Method : GET     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize can be commented for PostMan Testing
	   */

	
	@PreAuthorize("hasAnyRole('ADMIN', 'BUSINESS', 'INDIVIDUAL')")
	@RequestMapping(method = RequestMethod.GET, value = "/users/{username}")
	public User getUser(@PathVariable("username") String username) {
		return userService.getUser(username);
	}

  
  
 

  
  
  
  
}
