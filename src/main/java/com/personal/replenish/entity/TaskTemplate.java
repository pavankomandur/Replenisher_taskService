package com.personal.replenish.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;

/**
 * 
 *
 * <p>Task Template is used to create tasks simultaneously for multiple users.
 * and it can be used to schedule in recurring mode also.
 */
@Entity
public class TaskTemplate extends BaseEntity {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String name;

  @Column(length = 65535, columnDefinition = "Text")
  @Type(type = "text")
  private String description;

  @Column(length = 65535, columnDefinition = "Text")
  @Type(type = "text")
  private String notes;

  @Enumerated(EnumType.STRING)
  private TaskPriority taskPriority;
  private long estimatedDuration;
  
  @Column(length = 3, columnDefinition = "Text")
  @Type(type = "text")
  private String isRecurring;
  
  /**
   * 
   *
   * <p>recurringPeriodCronExpression : This is a cron expression to schedule Recurring Tasks.
   * when it is set, Timer job will schedule the recurring tasks.
   */
  private String recurringPeriodCronExpression;

   
  private String assigneeIds;
  
  /**
   * <p>User who created the task
   */
  private String reportedId;
  
  
  
public String getAssigneeIds() {
	return assigneeIds;
}
public void setAssigneeIds(String assigneeIds) {
	this.assigneeIds = assigneeIds;
}
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getNote() {
	return notes;
}
public void setNote(String note) {
	this.notes = note;
}
public TaskPriority getTaskPriority() {
	return taskPriority;
}
public void setTaskPriority(TaskPriority taskPriority) {
	this.taskPriority = taskPriority;
}
public long getEstimatedDuration() {
	return estimatedDuration;
}
public void setEstimatedDuration(long estimatedDuration) {
	this.estimatedDuration = estimatedDuration;
}

public String getIsRecurring() {
	return isRecurring;
}
public void setIsRecurring(String isRecurring) {
	this.isRecurring = isRecurring;
}
public String getRecurringPeriodCronExpression() {
	return recurringPeriodCronExpression;
}
public void setRecurringPeriodCronExpression(String recurringPeriodCronExpression) {
	this.recurringPeriodCronExpression = recurringPeriodCronExpression;
}

public String getReportedId() {
	return reportedId;
}
public void setReportedId(String reportedId) {
	this.reportedId = reportedId;
}
 

   
  
}
