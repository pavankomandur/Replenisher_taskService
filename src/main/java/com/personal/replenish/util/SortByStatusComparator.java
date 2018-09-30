package com.personal.replenish.util;

import java.util.Comparator;

import com.personal.replenish.entity.TaskStatus;
import com.personal.replenish.model.TaskTO;

public class SortByStatusComparator implements Comparator<TaskTO> {
	
	/**
	   *This class is used to sort the tasks by Status 
	   * 
	   */

  @Override
  public int compare(TaskTO o1, TaskTO o2) {
   
  
	  if (o1.getTaskStatus().equals(o2.getTaskStatus())) {
	      return 0;
	    } else {
	      TaskStatus p1 = TaskStatus.valueOf(o1.getTaskStatus());
	      TaskStatus p2 = TaskStatus.valueOf(o2.getTaskStatus());
	      
	      if (p1.equals(TaskStatus.IN_PROGRESS)) {
	        return -1;
	      } else if (p1.equals(TaskStatus.ASSIGNED) && p2.equals(TaskStatus.READY)) {
	        return -1;
	      } else if (p1.equals(TaskStatus.ASSIGNED) && p2.equals(TaskStatus.IN_PROGRESS)) {
	        return 1;
	      } else {
	        return 1;
	      }
	    }
	  }
  
  
  }

 


