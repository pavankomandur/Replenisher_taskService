package com.personal.replenish.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.replenish.entity.TaskTemplate;

@Repository
public interface TaskTemplateRepository extends JpaRepository<TaskTemplate, Long> {

	//List<TaskTemplate> findByReportedId(String reportedId);
}