package com.personal.replenish.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personal.replenish.entity.User;
import com.personal.replenish.service.UserService;

@RequestMapping("/admin")
@RestController
public class AdminController {

	static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public User createAdmin(@RequestBody User user) {
		log.debug("Inside Create Admin>>>>>>>>>>>>>>>>>");
		User tempUser = userService.createAdmin(user);
		log.debug("Admin> created Successfully >>>>>>>>>>>>>>>>");
		return tempUser;
		
	}
	
	
}
