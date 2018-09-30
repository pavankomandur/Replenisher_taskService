package com.personal.replenish.util;

import java.util.Comparator;
import java.util.Date;

import com.personal.replenish.entity.TaskPriority;
import com.personal.replenish.entity.TaskStatus;
import com.personal.replenish.model.TaskTO;

/**
 *This class is used to sort the tasks BASED ON STATUS FIRST AND THEN inputed priority against time estimated to perform the task 
 * 
 */

public class SortByStatusAndRank implements Comparator<TaskTO> {
	
	 @Override
	  public int compare(TaskTO o1, TaskTO o2) {
		
		 int priorityComparisonResult = compareTwoStatuses(o1, o2);
			    if (priorityComparisonResult == 0) {
			      return compareTwoPrioritiesWithRank(o1, o2);
			    } else {
			      return priorityComparisonResult;
			    }
	 	 
	 }
	 
	 public static int compareTwoStatuses(TaskTO o1, TaskTO o2)
	 {
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
	 
	 
	 
	 
	  
	  public static int compareTwoPrioritiesWithRank(TaskTO o1, TaskTO o2) {
	    int priorityComparisonResult =
	        TaskPriority.compareTwoPriorities(o1.getTaskPriority(), o2.getTaskPriority());
	    if (priorityComparisonResult == 0) {
	      return compareTwoTime(o1.getEstimatedTimeOfFinish(), o2.getEstimatedTimeOfFinish());
	    } else {
	      return priorityComparisonResult;
	    }
	  }

	  private static int compareTwoTime(Date d1, Date d2) {
	    long difference = d2.getTime() - d1.getTime();
	    if (difference == 0) {
	      return 0;
	    } else if (difference > 0) {
	      return 1;
	    } else {
	      return -1;
	    }
	  }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 }


