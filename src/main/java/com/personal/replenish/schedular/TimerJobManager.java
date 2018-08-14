package com.personal.replenish.schedular;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.personal.replenish.exception.RecurringJobException;

@Service
public class TimerJobManager {
  private static final Logger logger = LoggerFactory.getLogger(TimerJobManager.class);

  private Timer timer;
  private Map<String, TimerTask> timerJobMap;

  public TimerJobManager() {
    this.timerJobMap = new HashMap<>();
    this.timer = new Timer();
  }
  /** Start schedule task. */
  public void startJob(TimerJob timerJob, String jobId, Date nextExecutionTime, long period) {
    Date date = new Date();
    long currentTime = date.getTime();
    long startTime = nextExecutionTime.getTime();
    long delay;
    if (currentTime > startTime) {
      delay = period - (currentTime - startTime) % period;
    } else {
      delay = startTime - currentTime;
    }
    this.scheduleJob(jobId, timerJob, delay, period);
  }

  /**
   * start the job with customized delay and period, and save the job into memory. If the job
   * exists, delete the job first and start a new job.
   *
   * @param jobId name of the job.
   * @param timerJob object of the job.
   * @param delay delay in milliseconds before job is to be executed.
   * @param period time in milliseconds between successive job executions.
 * @throws RecurringJobException 
   */
  public void scheduleJob(String jobId, TimerTask timerJob, long delay, long period)  {
    try {
      timer.schedule(timerJob, delay, period);
      if (timerJobMap.containsKey(jobId)) {
        timerJobMap.get(jobId).cancel();
        timerJobMap.remove(jobId);
      }
      timerJobMap.put(jobId, timerJob);
    } catch (Exception e) {
    	throw new RecurringJobException("Exception Occurred while Job is stopped" + e.getMessage());
 }
  }

  /**
   * stop a specific job saved in memory if the job is already started.
   *
   * @param jobId the id of the job which is going to be stopped.
 * @throws RecurringJobException 
   */
  public void stopJob(String jobId) {
    TimerTask timerJob = timerJobMap.get(jobId);
    try {
      timerJob.cancel();
      timerJobMap.remove(jobId);
    } catch (Exception e) {
    	throw new RecurringJobException("Exception Occurred while Job is stopped" + e.getMessage());
  }
  }

  /** remove all jobs in the timer and clear the memory. */
  public void removeAllJobs() {
    timerJobMap.clear();
    timer.cancel();
    timer.purge();
  }
}
