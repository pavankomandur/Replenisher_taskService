package com.personal.replenish.schedular;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mockito.internal.junit.ExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;

import com.personal.replenish.entity.TaskTemplate;
import com.personal.replenish.exception.RecurringJobException;
/**
 *This class : CronJobService will initialise the job
 *
 *    
* @ All Exceptions are handled through centralized exceptional handling 
* 
 */

@Service
public class CronJobService {
  private static final Logger logger = LoggerFactory.getLogger(CronJobService.class);
  /**
   * This 'map' variable is used for storing association relationship between task and
   * CronSequenceGenerator. Each task should only have one cron sequece generator.
   */
  private Map<Long, CronSequenceGenerator> map = new HashMap<>();

  /**
   * init() function will take a created/edited task as input. Extract recurring period cron job
   * expression and task id from task.
   *
   * @param taskTemplate  will provide id and recurring period cron expression for the
   *     task if it is a recurring taskTemplate.
   */
  public void init(TaskTemplate taskTemplate) {
    String cronExpression = taskTemplate.getRecurringPeriodCronExpression();
    Long taskTemplateId = taskTemplate.getId();
    CronSequenceGenerator generator = new CronSequenceGenerator(cronExpression);
    if (map.containsKey(taskTemplateId)) {
      logger.info(
          String.format(
              "Updating cron sequence generator for task template id %s",
              Long.toString(taskTemplateId)));
    }
    map.put(taskTemplateId, generator);
  }

  /**
   * calculate the exact execution time for next running.
   *
   * @param taskTemplateId
   * @return
 * @throws Exception 
   */
  public Date calculateNextJobExecutionDate(Long taskTemplateId)  {
    if (map.containsKey(taskTemplateId)) {
      CronSequenceGenerator generator = map.get(taskTemplateId);
      return generator.next(new Date());
    } else {

    	throw new RecurringJobException("Exception Occurred while Job is stopped");
    }
  }

  /**
   * calculate cron job running period
   *
   * @param taskTemplateId
   * @return
   */
  public long calculatePeriod(Long taskTemplateId) throws Exception {
    if (map.containsKey(taskTemplateId)) {
      CronSequenceGenerator generator = map.get(taskTemplateId);
      Date firstExecutionDate = generator.next(new Date());
      Date secondExecutionDate = generator.next(firstExecutionDate);
      return secondExecutionDate.getTime() - firstExecutionDate.getTime();
    } else {
//      throw ExceptionFactory.create(
//          ExceptionType.IllegalArgumentException,
//          "Invalid taskId provided for calculating cron period");
    	throw new Exception(new IllegalArgumentException());
    }
  }
}
