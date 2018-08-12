package com.personal.replenish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.replenish.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByAssigneeId(String assigneeId);
}