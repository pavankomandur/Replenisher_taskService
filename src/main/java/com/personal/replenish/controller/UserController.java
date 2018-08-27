package com.personal.replenish.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personal.replenish.entity.User;
import com.personal.replenish.model.UserTO;
import com.personal.replenish.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RequestMapping("replenisher/auth/users")
@RestController
@Api(value="Users")
public class UserController {

	static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	/**
	   *This Method is used to Create a User: Only Role with Admin can Create a user.
	   *
	   *@End Point : http://localhost:9090/replenisher/users/create
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *@Request : user : user object as json
	   *@Method : POST     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize can be commented for PostMan Testing
	   */
	
	@ApiOperation(value = "Creates a User", nickname = "Creates User")
	  @ApiResponses(value = { 
	          @ApiResponse(code = 200, message = "Success", response = User.class),
	          @ApiResponse(code = 401, message = "Unauthorized"),
	          @ApiResponse(code = 403, message = "Forbidden"),
	          @ApiResponse(code = 404, message = "Not Found"),
	          @ApiResponse(code = 500, message = "Failure")}) 	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/create",produces=MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody UserTO user) {
		System.out.println("Inside Create User ");
		return userService.createUser(user);
	}
	
	/**
	   *This Method is used to Update a User: Only Role with Admin can Update a user.
	   *
	   *@End Point : http://localhost:9090/replenisher/users/modify/{username}
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *@Request : user : user object
	   *@Method : PUT     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize can be commented for PostMan Testing
	   */
	
	@ApiOperation(value = "Updates a User", nickname = "Updates User")
	  @ApiResponses(value = { 
	          @ApiResponse(code = 200, message = "Success", response = User.class),
	          @ApiResponse(code = 401, message = "Unauthorized"),
	          @ApiResponse(code = 403, message = "Forbidden"),
	          @ApiResponse(code = 404, message = "Not Found"),
	          @ApiResponse(code = 500, message = "Failure")}) 	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/modify" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody UserTO userTO) {
		log.debug("Inside Update User ");
		return userService.updateUser(userTO);
	}
	
	/**
	   *This Method is used to retrieve all Users: Only Role with Admin can get all Users.
	   *
	   *@End Point : http://localhost:9090/replenisher/users/getAllUsers
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *   *@Method : GET     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize can be commented for PostMan Testing
	   */
	
	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/getAllUsers" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		log.debug("Inside get All Users");
		return userService.getAllUsers();
	}
	
	
	/**
	   *This Method is used to retrieve particular user details: all the Roles can access this feature.
	   *
	   *@End Point : http://localhost:9090/replenisher/users/{username}
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *@Request : username : username from path variable
	   *@Method : GET     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize can be commented for PostMan Testing
	   */

	
	//@PreAuthorize("hasAnyRole('ADMIN', 'BUSINESS', 'INDIVIDUAL')")
	@RequestMapping(method = RequestMethod.GET, value = "/{username}" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable("username") String username) {
		return userService.getUser(username);
	}

  
  
 

  
  
  
  
}
