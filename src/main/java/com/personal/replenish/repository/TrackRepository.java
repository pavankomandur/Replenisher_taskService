package com.personal.replenish.repository;


import com.personal.replenish.entity.TaskTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TrackRepository extends JpaRepository<TaskTrack, Long> {

    @Query("SELECT p FROM TaskTrack p WHERE LOWER(p.taskId) = LOWER(:task_id) order by time_created desc")
    public List<TaskTrack> findbytaskIdforTracking(@Param("task_id") String task_id);
}