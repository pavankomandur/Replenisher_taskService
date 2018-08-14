package com.personal.replenish.util;
import java.util.Comparator;
import java.util.Date;

import com.personal.replenish.entity.TaskPriority;
import com.personal.replenish.model.TaskTO;

public class SortByRankComparator implements Comparator<TaskTO> {
	
	/**
	   *This class is used to sort the tasks which weighs inputed priority against time estimated to perform the task 
	   * 
	   */

  @Override
  public int compare(TaskTO o1, TaskTO o2) {
    int priorityComparisonResult =
        TaskPriority.compareTwoPriorities(o1.getTaskPriority(), o2.getTaskPriority());
    if (priorityComparisonResult == 0) {
      return compareTwoTime(o1.getEstimatedTimeOfFinish(), o2.getEstimatedTimeOfFinish());
    } else {
      return priorityComparisonResult;
    }
  }

  private int compareTwoTime(Date d1, Date d2) {
    long difference = d1.getTime() - d2.getTime();
    if (difference == 0) {
      return 0;
    } else if (difference > 0) {
      return 1;
    } else {
      return -1;
    }
  }
}
