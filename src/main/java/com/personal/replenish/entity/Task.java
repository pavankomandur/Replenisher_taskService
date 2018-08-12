package com.personal.replenish.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;

@Entity
public class Task extends BaseEntity {
  /** task name */
  private String name;
  
  @Enumerated(EnumType.STRING)
  private TaskStatus taskStatus;

  @Enumerated(EnumType.STRING)
  private TaskPriority taskPriority;
 
  @Column(length = 60000, columnDefinition = "Text")
  @Type(type = "text")
  private String description;

  @Column(length = 60000, columnDefinition = "Text")
  @Type(type = "text")
  private String note;

  @Column(length = 60000, columnDefinition = "Text")
  @Type(type = "text")
  private String feedback;

  private Date estimatedTimeOfFinish;
  
  private String assigneeId;
  
  private String reportedById;
  

  public Date getEstimatedTimeOfFinish() {
	return estimatedTimeOfFinish;
}

public void setEstimatedTimeOfFinish(Date estimatedTimeOfFinish) {
	this.estimatedTimeOfFinish = estimatedTimeOfFinish;
}

public String getAssigneeId() {
	return assigneeId;
}

public void setAssigneeId(String assigneeId) {
	this.assigneeId = assigneeId;
}

public String getReportedById() {
	return reportedById;
}

public void setReportedById(String reportedById) {
	this.reportedById = reportedById;
}

public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TaskStatus getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(TaskStatus taskStatus) {
    this.taskStatus = taskStatus;
  }

  public TaskPriority getTaskPriority() {
    return taskPriority;
  }

  public void setTaskPriority(TaskPriority taskPriority) {
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

 

  
}
