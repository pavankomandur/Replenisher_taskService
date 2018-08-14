package com.personal.replenish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.replenish.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	/**
	   *This Method is used to retrieve all the Tasks which are assigned to the user passed in
	   *@Param : assigneeId :assigned User
	   *    
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * 
	   */
	List<Task> findByAssigneeId(String assigneeId);
	
	/**
	   *This Method is used to retrieve all the Tasks which are reported by  the user passed in
	   *@Param : reportedId :reported User
	   *    
	 * @ All Exceptions are handled through centralized exceptional handling 
	 * 
	   */
	List<Task> findByReportedById(String reportedId);
	//Task findById(String taskId);
	
	
	//void deleteById(Long taskId);
}