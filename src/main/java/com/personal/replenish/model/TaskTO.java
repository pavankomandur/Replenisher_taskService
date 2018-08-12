package com.personal.replenish.model;


import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.replenish.entity.Task;

/**
 * TaskDTO used for creating and editing task object
 *
 * <p>For more fields information refer to {@link Task}
 */
public class TaskTO {
  private Long taskId;
  private String name;
  private String taskStatus;
  private String taskPriority;
  private String description;
  private String note;
  private String feedback;
  private Date estimatedTimeOfFinish;
  private String assignedUserId;
  private String reportedUserId;
public Long getTaskId() {
	return taskId;
}
public void setTaskId(Long taskId) {
	this.taskId = taskId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTaskStatus() {
	return taskStatus;
}
public void setTaskStatus(String taskStatus) {
	this.taskStatus = taskStatus;
}
public String getTaskPriority() {
	return taskPriority;
}
public void setTaskPriority(String taskPriority) {
	this.taskPriority = taskPriority;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public String getFeedback() {
	return feedback;
}
public void setFeedback(String feedback) {
	this.feedback = feedback;
}
public Date getEstimatedTimeOfFinish() {
	return estimatedTimeOfFinish;
}
public void setEstimatedTimeOfFinish(Date estimatedTimeOfFinish) {
	this.estimatedTimeOfFinish = estimatedTimeOfFinish;
}
public String getAssignedUserId() {
	return assignedUserId;
}
public void setAssignedUserId(String assignedUserId) {
	this.assignedUserId = assignedUserId;
}
public String getReportedUserId() {
	return reportedUserId;
}
public void setReportedUserId(String reportedUserId) {
	this.reportedUserId = reportedUserId;
}
  
 
 
 

 
}
