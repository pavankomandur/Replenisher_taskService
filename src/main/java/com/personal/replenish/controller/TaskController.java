package com.personal.replenish.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.replenish.model.TaskTO;
import com.personal.replenish.model.TaskTemplateDTO;
import com.personal.replenish.service.TaskService;

@CrossOrigin
@RequestMapping("replenisher/tasks")
@RestController
public class TaskController {
	
	@Autowired
	TaskService taskService;

  
  @RequestMapping(value = "/createTask", method = RequestMethod.POST ,consumes  = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> getTaskList(@RequestBody @Valid TaskTO req) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Inside getTaskList : Logged in User is " + auth.getName());
		System.out.println("Inside getTaskList : Task Details are: " + req.getName());
		System.out.println("Inside getTaskList : Task Details are: " + req.getTaskPriority());
		System.out.println("Inside getTaskList : Task Details are: " + req.getTaskStatus());
		boolean result=taskService.addTask(req);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
  }
  
  
  
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/task/sort/rank", method = RequestMethod.GET)
  public ResponseEntity<String> getTaskListSortByRank() {
    return new ResponseEntity<>("", HttpStatus.OK);
  }
  
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')") 
  @RequestMapping(value = "/updateTask", method = RequestMethod.PUT)
  public ResponseEntity<String> updateTask(@RequestBody TaskTO taskDTO) {
       return new ResponseEntity<>("success", HttpStatus.OK);
  }
  
  
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/deleteTask", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteTask(@RequestParam Long taskId) {
   
    return new ResponseEntity<>("success", HttpStatus.OK);
  }
 
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/task/template", method = RequestMethod.POST)
  public ResponseEntity<String> createTaskTemplate(
      @RequestBody TaskTemplateDTO taskTemplateDTO) {
	return new ResponseEntity<String>("success", HttpStatus.OK);
  }
  
  
  
  
}
