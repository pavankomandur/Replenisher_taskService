package com.personal.replenish.model;


import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.replenish.entity.TaskPriority;

public class TaskTemplateTO {
  private Long taskTemplateId;
  private String name;
  private String description;
  private String notes;
  private String taskPriority;
  private long estimatedDuration;
  private String isRecurring;
  private String recurringPeriodCronExpression;
  private String assigneeIds;
  private String reportedId;
  
  
public Long getTaskTemplateId() {
	return taskTemplateId;
}
public void setTaskTemplateId(Long taskTemplateId) {
	this.taskTemplateId = taskTemplateId;
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
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
}
public String getTaskPriority() {
	return taskPriority;
}
public void setTaskPriority(String taskPriority) {
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


public String getAssigneeIds() {
	return assigneeIds;
}
public void setAssigneeIds(String assigneeIds) {
	this.assigneeIds = assigneeIds;
}
public String getReportedId() {
	return reportedId;
}
public void setReportedId(String reportedId) {
	this.reportedId = reportedId;
}
  
  
 
  
 
}
