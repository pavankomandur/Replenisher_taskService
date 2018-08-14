package com.personal.replenish.schedular;


import java.util.TimerTask;

/**
 *This Class is used to run the task as scheduled.
 *
 *    
* @ All Exceptions are handled through centralized exceptional handling 
* 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.personal.replenish.entity.TaskTemplate;
import com.personal.replenish.service.TaskService;

public class TimerJob extends TimerTask {
	Logger log = LoggerFactory.getLogger(TimerJob.class);

  private TaskTemplate newTaskTemplate;
  private TaskService taskService;

  public TimerJob(TaskTemplate taskTemplate, TaskService taskService) {
    TaskTemplate newTaskTemplate = new TaskTemplate();
    newTaskTemplate.setName(taskTemplate.getName());
    newTaskTemplate.setDescription(taskTemplate.getDescription());
    newTaskTemplate.setNote(taskTemplate.getNote());
    newTaskTemplate.setEstimatedDuration(taskTemplate.getEstimatedDuration());
    newTaskTemplate.setTaskPriority(taskTemplate.getTaskPriority());
    
    newTaskTemplate.setIsRecurring(taskTemplate.getIsRecurring());
    newTaskTemplate.setRecurringPeriodCronExpression(
        taskTemplate.getRecurringPeriodCronExpression());
    newTaskTemplate.setAssigneeIds(taskTemplate.getAssigneeIds());
    this.newTaskTemplate = newTaskTemplate;
    this.taskService = taskService;
  }

  /** scheduled job will run periodically to create a task in based on */
  @Override
  public void run() {
     log.debug("Recurring job is running");
    taskService.createTaskFromTaskTemplate(newTaskTemplate);
  }
}
