package com.personal.replenish.service.impl;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.personal.replenish.entity.Task;
import com.personal.replenish.entity.TaskTemplate;
import com.personal.replenish.model.TaskTO;
import com.personal.replenish.model.TaskTemplateTO;
import com.personal.replenish.repository.TaskRepository;
import com.personal.replenish.repository.TaskTemplateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
	
	@Mock
	TaskServiceImpl mockTaskServiceImpl;
	
	@Mock
	TaskRepository mockRepository;
	
	@Mock
	Task mockTask;
	
	@Mock
	TaskTO mockTaskTO;
	
	@Mock
	List<TaskTO> mockListTO;
	
	@Mock
	List<Task> mockTaskList;
	
	@Mock
	TaskTemplate mocktaskTemplate;
	
	@Mock
	TaskTemplateTO mocktaskTemplateTO;
	
	@Mock
	TaskTemplateRepository taskTemplateRepository;
	
	
	
	
	@Before
    public void setup() throws IOException {
        
    }

	

	@Test
	public void addTaskTest()
	{
		//TaskServiceImpl taskServiceImpl = PowerMockito.spy(this.mockTaskServiceImpl);
//		Mockito.when(mockTaskServiceImpl.convertTO(Mockito.any(),Mockito.anyString(),Mockito.anyString())).thenReturn(mockTask);
//		
		Mockito.doReturn(mockTask).when(mockTaskServiceImpl).convertTO(Mockito.any(), Mockito.any(), Mockito.any());
		Mockito.when(mockRepository.saveAndFlush(mockTask)).thenReturn(mockTask);
		mockTaskServiceImpl.addTask(Mockito.any(),Mockito.any());
	}

	
	@Test
	public void getTasksByUserTest()
	{	
		Mockito.when(mockRepository.findByAssigneeId(Mockito.anyString())).thenReturn(mockTaskList);
		Mockito.doReturn(mockListTO).when(mockTaskServiceImpl).convertToModel(mockTaskList);
		mockTaskServiceImpl.getTasksByUser(Mockito.any(),Mockito.any());
	}
	
	
	@Test
	public void updateTaskTest()
	{
		//TaskServiceImpl taskServiceImpl = PowerMockito.spy(this.mockTaskServiceImpl);
//		Mockito.when(mockTaskServiceImpl.convertTO(Mockito.any(),Mockito.anyString(),Mockito.anyString())).thenReturn(mockTask);
//		
		Mockito.doReturn(mockTask).when(mockTaskServiceImpl).convertTO(Mockito.any(), Mockito.any(), Mockito.any());
		Mockito.when(mockRepository.saveAndFlush(mockTask)).thenReturn(mockTask);
		mockTaskServiceImpl.updateTask(Mockito.any(),Mockito.any());
	}
	
	
	
	@Test
	public void deleteTaskTest()
	{
		//TaskServiceImpl taskServiceImpl = PowerMockito.spy(this.mockTaskServiceImpl);
//		Mockito.when(mockTaskServiceImpl.convertTO(Mockito.any(),Mockito.anyString(),Mockito.anyString())).thenReturn(mockTask);
//		
		//Mockito.doReturn(true).when(mockRepository).deleteById(Mockito.anyLong());
		mockTaskServiceImpl.deleteTask(Mockito.anyString());
	}
	
	@Test
	public void addTemplateTest()
	{

		//Mockito.doReturn(mocktaskTemplate).when(mockTaskServiceImpl).convertTemplateTO(Mockito.any(),Mockito.anyString());
		//Mockito.when(mockRepository.saveAndFlush(mockTask)).thenReturn(mockTask);
		mockTaskServiceImpl.addTemplateTask(mockTaskTemplateTO());
	}
	
	public TaskTemplateTO mockTaskTemplateTO()
	{
		
		TaskTemplateTO mocktaskTemplateTO=new TaskTemplateTO();
		mocktaskTemplateTO.setAssigneeIds("abc");
		mocktaskTemplateTO.setDescription("Description");
		mocktaskTemplateTO.setEstimatedDuration(23);
		mocktaskTemplateTO.setIsRecurring("NO");
		mocktaskTemplateTO.setName("mock Name");
		mocktaskTemplateTO.setNotes("mockNotes");
		mocktaskTemplateTO.setRecurringPeriodCronExpression("test cron expression");
		mocktaskTemplateTO.setReportedId("myself");
		mocktaskTemplateTO.setTaskPriority("HIGH");
		mocktaskTemplateTO.setTaskTemplateId((long) 9090109);
		return mocktaskTemplateTO;
		
		
	}


	public TaskTO mockTaskTO()
	{

		TaskTO mocktaskTO=new TaskTO();
		mocktaskTO.setAssignedUserId("abc");
		mocktaskTO.setDescription("Description");
		mocktaskTO.setEstimatedDuration(23);
		mocktaskTO.setName("mock Name");
		mocktaskTO.setNote("mockNotes");
		mocktaskTO.setReportedUserId("myself");
		mocktaskTO.setTaskPriority("HIGH");
		return mocktaskTO;


	}



	@Test
	public void updateTemplateTest()
	{

		//Mockitto.doReturn(mocktaskTemplate).when(mockTaskServiceImpl).convertTemplateTO(Mockito.any(),Mockito.anyString());
		//Mockito.when(mockRepository.saveAndFlush(mockTask)).thenReturn(mockTask);
		mockTaskServiceImpl.updateTemplateTask(mockTaskTemplateTO());
	}


	@Test
	public void convertTOwithIN_ProgressTest()
	{

		//Mockito.doReturn(mocktaskTemplate).when(mockTaskServiceImpl).convertTemplateTO(Mockito.any(),Mockito.anyString());
		//Mockito.when(mockRepository.saveAndFlush(mockTask)).thenReturn(mockTask);
		mockTaskServiceImpl.convertTO(mockTaskTO(),"IN_PROGRESS","user1");
	}

	@Test
	public void convertTOwithReadyTest()
	{

		//Mockito.doReturn(mocktaskTemplate).when(mockTaskServiceImpl).convertTemplateTO(Mockito.any(),Mockito.anyString());
		//Mockito.when(mockRepository.saveAndFlush(mockTask)).thenReturn(mockTask);
		mockTaskServiceImpl.convertTO(mockTaskTO(),"READY","user1");
	}


	@Test
	public void getTestByIdTest()
	{

		//Mockito.doReturn(mockTask).when(mockRepository).findById(Mockito.anyLong());
		Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(mockTask));
		mockTaskServiceImpl.getTaskbyId(Mockito.anyString());

	}


}

