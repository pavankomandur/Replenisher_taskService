package com.personal.replenish.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.replenish.entity.TaskTemplate;
import com.personal.replenish.model.TaskTO;
import com.personal.replenish.model.TaskTemplateTO;

@Service
public interface TaskService {
	
	public boolean addTask(TaskTO taskto,String loggedinUser);
	public TaskTO getTaskbyId(String taskId);
	public List<TaskTO> getTasksByUser(String userId,String idType);
	public boolean updateTask(TaskTO taskto,String loggedinUser);
	public boolean deleteTask(String taskId);
	public void createTaskFromTaskTemplate(TaskTemplate taskTemplate);
	public boolean addTemplateTask(TaskTemplateTO taskTemplateto);
	public boolean updateTemplateTask(TaskTemplateTO taskTemplateto);
	public List<TaskTO> getallTasks();

}
