package com.personal.replenish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.replenish.entity.Task;
import com.personal.replenish.entity.TaskPriority;
import com.personal.replenish.entity.TaskStatus;
import com.personal.replenish.model.TaskTO;
import com.personal.replenish.repository.TaskRepository;
import com.personal.replenish.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskRepository repository;
	
	public boolean addTask(TaskTO taskto)
	{
		Task task=convertTO(taskto);
		repository.save(task);
		return true;
	}

	@Override
	public TaskTO getTask(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getTasks(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTask(TaskTO taskto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTask(String taskId) {
		// TODO Auto-generated method stub
		
	}
	
	public Task convertTO(TaskTO taskto)
	{
		TaskStatus taskStatus = TaskStatus.lookup(taskto.getTaskStatus());
		TaskPriority priority=TaskPriority.lookup(taskto.getTaskPriority());
		Task task=new Task();
		task.setAssigneeId(taskto.getAssignedUserId());
		task.setDescription(taskto.getDescription());
		task.setEstimatedTimeOfFinish(taskto.getEstimatedTimeOfFinish());
		task.setFeedback(taskto.getFeedback());
		task.setName(taskto.getName());
		task.setNote(taskto.getNote());
		task.setReportedById(taskto.getReportedUserId());
		task.setTaskPriority(priority);
		task.setTaskStatus(taskStatus);
		return task;
	}
	
	

}
