package com.personal.replenish.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.replenish.entity.Task;
import com.personal.replenish.model.TaskTO;

@Service
public interface TaskService {
	
	public boolean addTask(TaskTO taskto);
	public TaskTO getTask(String taskId);
	public List<Task> getTasks(String userId);
	public void updateTask(TaskTO taskto);
	public void deleteTask(String taskId);

}
