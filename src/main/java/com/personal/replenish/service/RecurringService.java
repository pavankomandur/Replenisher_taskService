package com.personal.replenish.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.replenish.entity.TaskTemplate;
import com.personal.replenish.schedular.CronJobService;
import com.personal.replenish.schedular.TimerJob;
import com.personal.replenish.schedular.TimerJobManager;

@Service
public class RecurringService {
  @Autowired 
  private TimerJobManager timerJobManager;
  @Autowired 
  private TaskService taskService;
  @Autowired 
  private CronJobService cronJobService;
  
  /**
   * Creates a Recurring Job and schedules it. calles Timer Job to execute the Task creation
   * First it Initialises the Job, then Calculates next exection Time and with the help of timer job, It executes the task.
   *
   * @param taskTemplate object.
   * 
   */

  public void createRecurringJob(TaskTemplate taskTemplate) throws Exception {
    cronJobService.init(taskTemplate);
    Date nextJobExecutionDate = cronJobService.calculateNextJobExecutionDate(taskTemplate.getId());
    long period = cronJobService.calculatePeriod(taskTemplate.getId());
    TimerJob timerJob = new TimerJob(taskTemplate, taskService);
    timerJobManager.startJob(
        timerJob, Long.toString(taskTemplate.getId()), nextJobExecutionDate, period);
  }

  public void updateRecurringJob(TaskTemplate taskTemplatek) throws Exception {
    deleteRecurringJob(taskTemplatek);
    createRecurringJob(taskTemplatek);
  }

  public void deleteRecurringJob(TaskTemplate taskTemplate) {
    timerJobManager.stopJob(Long.toString(taskTemplate.getId()));
  }
}
