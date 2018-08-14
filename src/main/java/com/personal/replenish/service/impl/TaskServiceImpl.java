package com.personal.replenish.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.replenish.entity.Task;
import com.personal.replenish.entity.TaskPriority;
import com.personal.replenish.entity.TaskStatus;
import com.personal.replenish.entity.TaskTemplate;
import com.personal.replenish.model.TaskTO;
import com.personal.replenish.model.TaskTemplateTO;
import com.personal.replenish.repository.TaskRepository;
import com.personal.replenish.repository.TaskTemplateRepository;
import com.personal.replenish.service.RecurringService;
import com.personal.replenish.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskRepository repository;
	
	
	@Autowired
	RecurringService recurringService;
	
	
	@Autowired
	TaskTemplateRepository templateRepository;
	
	 private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	 
	 /**
	   *Creates the Task. Individuals can create a Task using this method
	   *
	   * @param taskto : Task Transfer Object
	   * @param loggedinUser : Logged in User.
	   * 
	   */
	
	public boolean addTask(TaskTO taskto,String loggedinUser)
	{
		if (taskto!=null) {
			Task task=convertTO(taskto,"save",loggedinUser);
			int i=1;
			int y=i/0;
			repository.saveAndFlush(task);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	   *This Method is used to get all Tasks by assignee/reported By
	   *
	   * @param idType : by whom whether it is by assignee or by reported by
	   * @param userId : Logged in User.
	   * 
	   */
	@Override
	public List<TaskTO> getTasksByUser(String userId,String idType) {
		List<Task> tasksByAssignee=new ArrayList<Task>();
		if (idType!=null && idType.equalsIgnoreCase("assignee")) {
			tasksByAssignee=repository.findByAssigneeId(userId); }
		else if (idType!=null && idType.equalsIgnoreCase("reported")) {
			tasksByAssignee=repository.findByReportedById(userId);
		}
		
		List<TaskTO> taskModel=convertToModel(tasksByAssignee);
		return taskModel;
	}

	/**
	   *This Method is used to update Tasks 
	   *
	   *@param taskto : Task Transfer Object
	   * @param loggedinUser : Logged in User.
	   * 
	   */
	@Override
	public boolean updateTask(TaskTO taskto,String loggedinUser) {
		if (taskto!=null) {
			Task task=convertTO(taskto,"update",loggedinUser);
			repository.save(task);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	   *This Method is used to Delete the  Tasks 
	   *
	   *@param taskId : Task Id which is to be deleted
	   * 
	   * 
	   */

	@Override
	public boolean deleteTask(String taskId) {
		if (taskId!=null) {
			repository.deleteById(Long.valueOf(taskId));
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	/**
	   *This Method is used to Convert Task Model object to Task Entity Object 
	   *
	   *@param taskto : Task Transfer Object
	   *@param status : whether is it Save or Update
	   **@param loggedinUser : Logged In user
	   */
	
	public Task convertTO(TaskTO taskto,String status,String loggedinUser)
	{
		TaskStatus taskStatus = TaskStatus.lookup(taskto.getTaskStatus());
		TaskPriority priority=TaskPriority.lookup(taskto.getTaskPriority());
		Task task=new Task();
		if (status.equalsIgnoreCase("update"))
		{
			task.setId(taskto.getTaskId());
		}
		task.setAssigneeId(taskto.getAssignedUserId());
		task.setDescription(taskto.getDescription());
		task.setEstimatedTimeOfFinish(taskto.getEstimatedTimeOfFinish());
		task.setFeedback(taskto.getFeedback());
		task.setName(taskto.getName());
		task.setNote(taskto.getNote());
		if (loggedinUser!=null) {
		task.setReportedById(loggedinUser);
		}
		else
		{
			task.setReportedById(taskto.getReportedUserId());
		}
		task.setEstimatedDuration(Long.valueOf(taskto.getEstimatedDuration()));
		task.setTaskPriority(priority);
		task.setTaskStatus(taskStatus);
		return task;
	}
	
	
	/**
	   *This Method is used to Convert Task Model object to Task Entity Object 
	   *
	   *@param taskto : Task Transfer Object
	   *@param status : whether is it Save or Update
	   **@param loggedinUser : Logged In user
	   */
	public List<TaskTO> convertToModel(List<Task> taskByAssignee)
	{
		List<TaskTO> listOfTasks=new ArrayList<TaskTO>();
		taskByAssignee.forEach(tasksByAssignee-> {
			TaskTO taskObject=new TaskTO();
			taskObject.setAssignedUserId(tasksByAssignee.getAssigneeId());
			taskObject.setDescription(tasksByAssignee.getDescription());
			taskObject.setEstimatedTimeOfFinish(tasksByAssignee.getEstimatedTimeOfFinish());
			taskObject.setFeedback(tasksByAssignee.getFeedback());
			taskObject.setName(tasksByAssignee.getName());
			taskObject.setNote(tasksByAssignee.getNote());
			taskObject.setReportedUserId(tasksByAssignee.getReportedById());
			taskObject.setTaskId(tasksByAssignee.getId());
			taskObject.setTaskPriority(tasksByAssignee.getTaskPriority().name());
			taskObject.setTaskStatus(tasksByAssignee.getTaskStatus().name());
			taskObject.setEstimatedDuration(Long.valueOf(tasksByAssignee.getEstimatedDuration()));
			listOfTasks.add(taskObject);
		});
		return listOfTasks;
	}

	/**
	   *This Method is used to get a Task by task Id 
	   *
	   *@param taskId : Task id 
	   *
	   */
	@Override
	public TaskTO getTaskbyId(String taskId) {
		TaskTO taskbyId=new TaskTO();
		if (taskId!=null) {
			
			Optional<Task> task=repository.findById(Long.valueOf(taskId));
			if (task!=null) {
				
				taskbyId= convertTaskToModel(task);
				
			} else {
				return null;
			}
		}
		else {
			return null; 
			}
		
		return taskbyId;
	}
	
	
	/**
	   *This Method is used to Convert Task object to Task Model Object 
	   *
	   *@param task : Task Object
	   *
	   */
	
	
	public TaskTO convertTaskToModel(Optional<Task> task)
	{
		if(task!=null) {
			TaskTO taskObject=new TaskTO();
			taskObject.setAssignedUserId(task.get().getAssigneeId());
			taskObject.setDescription(task.get().getDescription());
			taskObject.setEstimatedTimeOfFinish(task.get().getEstimatedTimeOfFinish());
			taskObject.setFeedback(task.get().getFeedback());
			taskObject.setName(task.get().getName());
			taskObject.setNote(task.get().getNote());
			taskObject.setReportedUserId(task.get().getReportedById());
			taskObject.setTaskId(task.get().getId());
			taskObject.setTaskPriority(task.get().getTaskPriority().name());
			taskObject.setTaskStatus(task.get().getTaskStatus().name());
			return taskObject;
		}
		else
			return null;
	}
	
	/**
	   *This Method is used to Create Template Task which is used for Scheduling Recurring Tasks for multiple users 
	   *
	   *@param taskTemplateto : TaskTemplate Transfer Object
	   *	After this object is saved, if the recurring is set to yes, then it will schedule the recurring tasks.
	 * @throws Exception 
	   */
	public boolean addTemplateTask(TaskTemplateTO taskTemplateto)
	{
		TaskTemplate taskTemplate=convertTemplateTO(taskTemplateto,"save");
		TaskTemplate ttemplate=templateRepository.saveAndFlush(taskTemplate);
		if (ttemplate.getIsRecurring().equalsIgnoreCase("Yes"))
		{
			try {
				recurringService.createRecurringJob(ttemplate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	   *This Method is used to Update Template Task which is used for Scheduling Recurring Tasks for multiple users 
	   *
	   *@param taskTemplateto : TaskTemplate Transfer Object
	   *	
	   */
	public boolean updateTemplateTask(TaskTemplateTO taskTemplateto)
	{
		TaskTemplate taskTemplate=convertTemplateTO(taskTemplateto,"update");
		templateRepository.saveAndFlush(taskTemplate);
		return true;
	}
	
	/**
	   *This Method is used to convert Template Task Model to Task Template Entity Object which is used for saving into Database 
	   *
	   *@param taskTemplateto : TaskTemplate Transfer Object
	   *@param status		 : whether it is save or update
	   *.
	   */
	public TaskTemplate convertTemplateTO(TaskTemplateTO taskTemplateto,String status)
	{
		
		TaskTemplate task=new TaskTemplate();
		if (status.equalsIgnoreCase("update"))
		{
			task.setId(taskTemplateto.getTaskTemplateId());
		}
		
		TaskPriority priority=TaskPriority.lookup(taskTemplateto.getTaskPriority());
		task.setAssigneeIds(taskTemplateto.getAssigneeIds());
		task.setDescription(taskTemplateto.getDescription());
		task.setEstimatedDuration(taskTemplateto.getEstimatedDuration());
		task.setName(taskTemplateto.getName());
		task.setNote(taskTemplateto.getNotes());
		task.setIsRecurring(taskTemplateto.getIsRecurring());
		task.setRecurringPeriodCronExpression(taskTemplateto.getRecurringPeriodCronExpression());
		task.setReportedId(taskTemplateto.getReportedId());
		task.setTaskPriority(priority);
		
		return task;
	}
	
	
	/**
	   *This Method is used to Create Task from  Template Task.
	   *This method will be called from Timer Job. when Recurring is scheduled, Internally from timerjob, the below 
	   *method will be called
	   *
	   *@param TaskTemplate : TaskTemplate Entity Object
	   *
	   *.
	   */
	 public void createTaskFromTaskTemplate(TaskTemplate taskTemplate) {
		    Task task = new Task();
		    task = convertTaskTemplateToTask(taskTemplate, task);
		    //iterate and loop depending upon number of assignees
		    Task newTask = createTask(task);
		    System.out.println("Created a task from task template successfully!");
		  }
	 
	 
	 private Task convertTaskTemplateToTask(TaskTemplate taskTemplate, Task task) {
		    task.setName(taskTemplate.getName());
		    task.setDescription(taskTemplate.getDescription());
		    task.setNote(taskTemplate.getNote());
		    task.setTaskPriority(taskTemplate.getTaskPriority());
		    task.setTaskStatus(TaskStatus.READY);
		    task.setNote(taskTemplate.getNote());
		    task.setReportedById(taskTemplate.getReportedId());
		   
		    //task.setTimeInput(new java.util.Date());
//		    task.setTimeEstimatedFinish(
//		        DateUtils.addMilliseconds(
//		            task.getTimeInput(), toIntExact(taskTemplate.getEstimatedDuration())));
		    return task;
		  }
	 
	 /**
	   *This Method is used to Create Task from Template Task which will be called from createTaskFromTaskTemplate method.
	   *This origination will be from Timer Job. when Recurring is scheduled, Internally from timerjob, the below 
	   *method will be called
	   *
	   *@param Task : Task Entity Object
	   *
	   *.
	   */
	 
	 public Task createTask(Task task) {
		    return repository.saveAndFlush(task);
		  }
	
	
}
