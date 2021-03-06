package com.personal.replenish.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.replenish.entity.TaskTrack;
import com.personal.replenish.model.TaskTO;
import com.personal.replenish.model.TaskTemplateTO;
import com.personal.replenish.service.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RequestMapping("replenisher/tasks")
@RestController
@Api(value="Tasks")
public class TaskController {
	
	
	
	@Autowired
	TaskService taskService;
	static final Logger log = LoggerFactory.getLogger(TaskController.class);
	
	/**
	   *This Method is used to Create Task : Individual or Business Users can Create a Task.
	   *@End Point : http://localhost:9090/replenisher/tasks/createTask
	   *@Headers : Accept : application/json
	   *          Content-Type : application/json
	   *@Request : Task Transfer Object 
	   *@Method : Post     
	   *
	   *
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * @PreAuthorize will check the logged in user role and will allow only those roles specified to that method 
	 * @PreAuthorize can be commented for PostMan Testing
      */
	
	@ApiOperation(value = "Create the Task", nickname = "Create Task")
	  @ApiResponses(value = { 
	          @ApiResponse(code = 200, message = "Success", response = Boolean.class),
	          @ApiResponse(code = 401, message = "Unauthorized"),
	          @ApiResponse(code = 403, message = "Forbidden"),
	          @ApiResponse(code = 404, message = "Not Found"),
	          @ApiResponse(code = 500, message = "Failure")}) 	
  @RequestMapping(value = "/createTask", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')") 
  public ResponseEntity<Boolean> createTask(@RequestBody @Valid TaskTO req) {
	  //String message="";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.debug("Inside getTaskList : Logged in User is " + auth.getName());
		log.debug("Inside getTaskList : Task Details are: " + req.getName());
		log.debug("Inside getTaskList : Task Details are: " + req.getTaskPriority());
		log.debug("Inside getTaskList : Task Details are: " + req.getTaskStatus());
		boolean result=taskService.addTask(req,auth.getName());
		//if (result) message="Successfully Created the Task";
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
  }
  
  /**
   *This Method is used to Update Task : Individual or Business Users can Update a Task.
   *@End Point : http://localhost:9090/replenisher/tasks/updateTask
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *@Request : Task Transfer Object 
   *@Method : Put     
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 * @PreAuthorize will check the logged in user role and will allow only those roles specified to that method 
 *  @PreAuthorize can be commented for PostMan Testing
   
   */
	@ApiOperation(value = "Updates the Task", nickname = "Update Task")
	  @ApiResponses(value = { 
	          @ApiResponse(code = 200, message = "Success", response = Boolean.class),
	          @ApiResponse(code = 401, message = "Unauthorized"),
	          @ApiResponse(code = 403, message = "Forbidden"),
	          @ApiResponse(code = 404, message = "Not Found"),
	          @ApiResponse(code = 500, message = "Failure")}) 		
	@PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')") 
  @RequestMapping(value = "/updateTask", method = RequestMethod.PUT)
  public ResponseEntity<Boolean> updateTask(@RequestBody TaskTO req) {
	  //String message="";
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  	log.debug("Inside getTaskList : Logged in User is " + auth.getName());
		log.debug("Inside getTaskList : Task Details are: " + req.getName());
		log.debug("Inside getTaskList : Task Details are: " + req.getTaskPriority());
		log.debug("Inside getTaskList : Task Details are: " + req.getTaskStatus());
		boolean result=taskService.updateTask(req,auth.getName());
		//if (result) message="Successfully Updated the Record";
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);    
  }
  
  /**
   *This Method is used to delete a Task : Individual or Business Users can Delete a Task.
   *@End Point : http://localhost:9090/replenisher/tasks/deleteTask
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *@Request : TaskId as Request Param   
   *@Method : Delete   
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 *  @PreAuthorize will check the logged in user role and will allow only those roles specified to that method 
 *  @PreAuthorize can be commented for PostMan Testing
  
   */
	
	
	@ApiOperation(value = "Deletes the Task", nickname = "Delete Task")
	  @ApiResponses(value = { 
	          @ApiResponse(code = 200, message = "Success", response = String.class),
	          @ApiResponse(code = 401, message = "Unauthorized"),
	          @ApiResponse(code = 403, message = "Forbidden"),
	          @ApiResponse(code = 404, message = "Not Found"),
	          @ApiResponse(code = 500, message = "Failure")}) 	
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/deleteTask", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteTask(@RequestParam(value="taskId" ,required=true) String taskId) {
	  log.debug("In TAsk Controller : To Delete " + taskId);
   boolean result=taskService.deleteTask(taskId);
    return new ResponseEntity<String>("success", HttpStatus.OK);
  }
  
  /**
   *This Method is used to get all Tasks by Assignee
   *@End Point : http://localhost:9090/replenisher/tasks/getAllTasksByAssignee/{userId}
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *@Request : User Id as Request Param 
   *@Method : GET     
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 * @PreAuthorize will check the logged in user role and will allow only those roles specified to that method 
 *  @PreAuthorize can be commented for PostMan Testing
   
   */
	@ApiOperation(value = "Retrieves all the Tasks by Assignee", nickname = "getAllTasksByAssignee ID")
	  @ApiResponses(value = { 
	          @ApiResponse(code = 200, message = "Success", response = TaskTO.class),
	          @ApiResponse(code = 401, message = "Unauthorized"),
	          @ApiResponse(code = 403, message = "Forbidden"),
	          @ApiResponse(code = 404, message = "Not Found"),
	          @ApiResponse(code = 500, message = "Failure")}) 		
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/getAllTasksByAssignee/{userId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaskTO>> getAllTaskbyAssignee(@PathVariable("userId") String userId) {
	  log.debug("In TAsk Controller : getAllTaskbyAssignee" + userId);
   		List<TaskTO> result=taskService.getTasksByUser(userId, "assignee");
    return new ResponseEntity<List<TaskTO>>(result, HttpStatus.OK);
  }
  
  /**
   *This Method is used to get all Tasks by ReportedBy
   *@End Point : http://localhost:9090/replenisher/tasks/getAllTasksByReported/{userId}
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *@Request : User Id as Request Param 
   *@Method : GET     
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 * @PreAuthorize will check the logged in user role and will allow only those roles specified to that method 
 *  @PreAuthorize can be commented for PostMan Testing
   
   */
	@ApiOperation(value = "Retrieves all the Tasks by Reported", nickname = "getAllTasksByReported ID")
	  @ApiResponses(value = { 
	          @ApiResponse(code = 200, message = "Success", response = TaskTO.class),
	          @ApiResponse(code = 401, message = "Unauthorized"),
	          @ApiResponse(code = 403, message = "Forbidden"),
	          @ApiResponse(code = 404, message = "Not Found"),
	          @ApiResponse(code = 500, message = "Failure")}) 		 
	
 @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/getAllTasksByReported/{userId}", method = RequestMethod.GET)
  public ResponseEntity<List<TaskTO>> getAllTaskbyReported(@PathVariable("userId") String userId) {
	  log.debug("In TAsk Controller : getAllTaskbyReported " + userId);
   		List<TaskTO> result=taskService.getTasksByUser(userId, "reported");
    return new ResponseEntity<List<TaskTO>>(result, HttpStatus.OK);
  }
  
  
  /**
   *This Method is used to get all the  Tasks
   *@End Point : http://localhost:9090/replenisher/tasks/getAllTasks
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *
   *@Method : GET     
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 * @PreAuthorize will check the logged in user role and will allow only those roles specified to that method 
 *  @PreAuthorize can be commented for PostMan Testing
   
   */
  
  @ApiOperation(value = "Retrieves all the Tasks ", nickname = "get All Task Records")
  @ApiResponses(value = { 
          @ApiResponse(code = 200, message = "Success", response = TaskTO.class),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 500, message = "Failure")}) 
  
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/getAllTasks", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaskTO>> getAllTask() {
	  log.debug("In TAsk Controller : getAllTasks");
   		List<TaskTO> result=taskService.getallTasks();
    return new ResponseEntity<List<TaskTO>>(result, HttpStatus.OK);
  }
  
  
  /**
   *This Method is used to get the Task by Id
   *@End Point : http://localhost:9090/replenisher/tasks/getTasksById/{taskId}
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *@Request : Task Id as Request Param 
   *@Method : GET     
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 * @PreAuthorize will check the logged in user role and will allow only those roles specified to that method 
 *  @PreAuthorize can be commented for PostMan Testing
   
   */
  
  @ApiOperation(value = "Retrieves all the Tasks by ID", nickname = "getAllTasksBy ID")
  @ApiResponses(value = { 
          @ApiResponse(code = 200, message = "Success", response = TaskTO.class),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 500, message = "Failure")}) 		
  
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/getTasksById/{taskId}", method = RequestMethod.GET)
  public ResponseEntity<TaskTO> getTaskById(@PathVariable("taskId") String taskId) {
	  log.debug("In TAsk Controller : getAgetTasksById " + taskId);
   		TaskTO result=taskService.getTaskbyId(taskId);
    return new ResponseEntity<TaskTO>(result, HttpStatus.OK);
  }
  
  
  /**
   *This Method is used to Create Template Task which is used to schedule Recurring Tasks for Individuals by Business
   *@End Point : http://localhost:9090/replenisher/tasks/createTaskTemplate
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *@Request : taskTemplateDTO : taskTemplateDTO as json
   *@Method : POST     
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 * @PreAuthorize will check the logged in user role and will allow only those roles specified to that method 
 *  @PreAuthorize can be commented for PostMan Testing
   */
  
  @ApiOperation(value = "Creates a Task Template", nickname = "create Task Template")
  @ApiResponses(value = { 
          @ApiResponse(code = 200, message = "Success", response = Boolean.class),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 500, message = "Failure")}) 		
 
  @PreAuthorize("hasAnyRole('BUSINESS')")
  @RequestMapping(value = "/createTaskTemplate", method = RequestMethod.POST)
  public ResponseEntity<Boolean> createTaskTemplate(
      @RequestBody TaskTemplateTO taskTemplateDTO) {
	  log.debug("Inside Create TAsk Template >>>>>>>>>>>>>>>>>>>>>>>>>");
	  boolean result=taskService.addTemplateTask(taskTemplateDTO);
	return new ResponseEntity<Boolean>(result, HttpStatus.OK);
  }
  
  
  /**
   *This Method is used to Update Template Task which is used to schedule Recurring Tasks for Individuals by Business
   *@End Point : http://localhost:9090/replenisher/tasks/updateTaskTemplate
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *@Request : taskTemplateDTO : taskTemplateDTO as json
   *@Method : POST     
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 * @PreAuthorize can be commented for PostMan Testing
   */
  
  @ApiOperation(value = "Updates a Task Template", nickname = "Update Task Template")
  @ApiResponses(value = { 
          @ApiResponse(code = 200, message = "Success", response = Boolean.class),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 500, message = "Failure")}) 	
  
  @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
  @RequestMapping(value = "/updateTaskTemplate", method = RequestMethod.POST)
  public ResponseEntity<Boolean> updateTaskTemplate(
      @RequestBody TaskTemplateTO taskTemplateDTO) {
	  log.debug("Inside Update TAsk Template >>>>>>>>>>>>>>>>>>>>>>>>>");
	  boolean result=taskService.updateTemplateTask(taskTemplateDTO);
	return new ResponseEntity<Boolean>(result, HttpStatus.OK);
  }
  
  
  /**
   *This Method is used to display all the Records of Task status Changes. This will be helpful in tracking how much
   *time the user spent on each task.
   *@End Point : http://localhost:9090/replenisher/tasks/getTaskForTraking/{taskId}
   *@Headers : Accept : application/json
   *          Content-Type : application/json
   *@Request : task Id : String
   *@Method : POST     
   *
   *
 * @ All Exceptions are handled through centralized exceptional handling 
 * @PreAuthorize can be commented for PostMan Testing
   */
  
  @ApiOperation(value = "Retrieves all the Task Status Records", nickname = "get All Task Status Records")
  @ApiResponses(value = { 
          @ApiResponse(code = 200, message = "Success", response = TaskTrack.class),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 403, message = "Forbidden"),
          @ApiResponse(code = 404, message = "Not Found"),
          @ApiResponse(code = 500, message = "Failure")}) 
  @ApiImplicitParams({
      @ApiImplicitParam(name = "taskId", value = "Task Id", required = true, dataType = "string")
    })
   @PreAuthorize("hasAnyRole('BUSINESS', 'INDIVIDUAL')")
	@RequestMapping(value = "/getTaskForTraking/{taskId}", method = RequestMethod.GET)
	public ResponseEntity<List<TaskTrack>> getTaskforTracking(@PathVariable("taskId") String taskId) {
		log.debug("In TAsk Controller : getAgetTasksById " + taskId);
		List<TaskTrack> result=taskService.getTaskForTracking(taskId);
		return new ResponseEntity<List<TaskTrack>>(result, HttpStatus.OK);
	}
  
  
}
