package com.personal.replenish.model;


import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskTemplateDTO {
  private Long taskTemplateId;
  private String name;
  private String description;
  private String note;
  private String taskPriorityString;
  private long estimatedDuration;
  private boolean isRecurring;
  private String recurringPeriodCronExpression;
  private List<String> assigneeId;
  private String reporterId;

  // required field for creating
  private Set<Long> assignedStaffIdSet;


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

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getTaskPriorityString() {
    return taskPriorityString;
  }

  public void setTaskPriorityString(String taskPriorityString) {
    this.taskPriorityString = taskPriorityString;
  }

  public long getEstimatedDuration() {
    return estimatedDuration;
  }

  public void setEstimatedDuration(long estimatedDuration) {
    this.estimatedDuration = estimatedDuration;
  }

  @JsonProperty(value = "isRecurring")
  public boolean isRecurring() {
    return isRecurring;
  }

  public void setRecurring(boolean recurring) {
    isRecurring = recurring;
  }

  public String getRecurringPeriodCronExpression() {
    return recurringPeriodCronExpression;
  }

  public void setRecurringPeriodCronExpression(String recurringPeriodCronExpression) {
    this.recurringPeriodCronExpression = recurringPeriodCronExpression;
  }

  
  public Set<Long> getAssignedStaffIdSet() {
    return assignedStaffIdSet;
  }

  public void setAssignedStaffIdSet(Set<Long> assignedStaffIdSet) {
    this.assignedStaffIdSet = assignedStaffIdSet;
  }

  
 
}
