package com.personal.replenish.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;

/**
 * For fields' documentations refer to {@link Task} domain.
 *
 * <p>Difference between Task and TaskTemplate: TaskTemplate does not have TaskStatus, feedback,
 * timeInput, timeEstimatedFinish (estimatedDuration instead),
 */
@Entity
public class TaskTemplate extends BaseEntity {
  private String name;

  @Column(length = 65535, columnDefinition = "Text")
  @Type(type = "text")
  private String description;

  @Column(length = 65535, columnDefinition = "Text")
  @Type(type = "text")
  private String note;

  @Enumerated(EnumType.STRING)
  private TaskPriority taskPriority;

  /** Frontend can use duration to calculate the estimated finishing date */
  private long estimatedDuration;

  /** boolean value to represent if the task is a recurring task */
  private boolean isRecurring;

  /**
   * if the boolean value above is true. This field is for defining the recurring periods with cron
   * job expression.
   *
   * <p>TODO: may change to use enum class with WEEKLY, MONTHLY, etc expressions.
   */
  private String recurringPeriodCronExpression;

  

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

  
  
}
