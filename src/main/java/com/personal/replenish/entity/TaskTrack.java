package com.personal.replenish.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class TaskTrack extends BaseEntity {

    private String taskId;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;


    private String assigneeId;

    private String reportedById;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
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

}