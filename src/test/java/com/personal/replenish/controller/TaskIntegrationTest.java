package com.personal.replenish.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.personal.replenish.model.TaskTO;
import com.personal.replenish.model.TaskTemplateTO;
import com.personal.replenish.security.JwtAuthenticationRequest;

public class TaskIntegrationTest extends Assert {
	
	RestTemplate template=new RestTemplate();
	
	private static final String BASE_URL="http://localhost:9090/replenisher";
	private static final String ALL_TASKS_URL="/tasks/getAllTasks";
	private static final String UPDATE_TASK_URL="/tasks/updateTask";
	private static final String CREATE_TASK_URL="/tasks/createTask";
	private static final String CREATE_TOKEN_URL="/auth/createToken";
	private static final String CREATE_USER_URL="auth/users/updateTask";
	private static final String CREATE_TASK_TEMPLATE_URL="/tasks/createTaskTemplate";
	private static final String CREATE_TASK_TRACKING_URL="/tasks/getTaskForTracking/125";
	
	public static String test_token="";
	
	
	@BeforeClass
	public static void test_get_token() throws JSONException {
		
		JwtAuthenticationRequest authenticationRequest=new JwtAuthenticationRequest();
		authenticationRequest.setUsername("pavan");
		authenticationRequest.setPassword("test123");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<JwtAuthenticationRequest> request = new HttpEntity<JwtAuthenticationRequest>(authenticationRequest,headers);
		RestTemplate template=new RestTemplate();
		ResponseEntity<String> result = template.exchange(
				BASE_URL + CREATE_TOKEN_URL, HttpMethod.POST, request,
				String.class);
		if (result != null && result.getStatusCode() != null
				&& result.getStatusCode().toString().equalsIgnoreCase("200")) {
			JSONObject obj = new JSONObject(result.getBody());
			//System.out.println("Token is " + obj.get("token"));
			test_token=(String) obj.get("token");
			assertNotNull(obj);
			
		}
		
		
	}
	
	@Test
	public void test_get_all_tasks() throws JSONException {
		
		//System.out.println("Token in this method is " + test_token);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + test_token);
		
		RestTemplate template=new RestTemplate();
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
       
		ResponseEntity<ArrayList> result = template.exchange(
				BASE_URL + ALL_TASKS_URL, HttpMethod.GET, entity,
				ArrayList.class);
        
       
		List<TaskTO> tasks=result.getBody();
        assertNotNull(tasks);
       
	}
	
	@Test
	public void test_create_tasks() throws JSONException {
		
		//System.out.println("Token in this method is " + test_token);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + test_token);
		
		TaskTO request =new TaskTO();
		request.setName("First Task from TestCase");
		request.setTaskStatus("READY");
		request.setTaskPriority("High");
		request.setDescription("Resolution");
		request.setNote("First Note");
		request.setFeedback("First FeedBack");
		request.setEstimatedTimeOfFinish(null);
		request.setAssignedUserId("pavan");
		request.setReportedUserId("pavan");
		
		
		RestTemplate template=new RestTemplate();
		
		HttpEntity<TaskTO> entity = new HttpEntity<TaskTO>(request, headers);
       
		ResponseEntity<Boolean> result = template.exchange(BASE_URL + CREATE_TASK_URL, HttpMethod.POST,entity,Boolean.class);
		if (result != null && result.getStatusCode() != null
				&& result.getStatusCode().toString().equalsIgnoreCase("200")) {
			
			assertTrue(result.getBody());
			
		}
       
   }
	
	@Test
	public void test_update_tasks() throws JSONException {
		
		//System.out.println("Token in this method is " + test_token);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + test_token);
		
		TaskTO request =new TaskTO();
		request.setName("First Task from TestCase");
		request.setTaskStatus("READY");
		request.setTaskPriority("High");
		request.setDescription("Resolution");
		request.setNote("First Note");
		request.setFeedback("First FeedBack");
		request.setEstimatedTimeOfFinish(null);
		request.setAssignedUserId("pavan");
		request.setReportedUserId("pavan");
		request.setTaskId(new Long(1));
		
		
		RestTemplate template=new RestTemplate();
		
		HttpEntity<TaskTO> entity = new HttpEntity<TaskTO>(request, headers);
       
		ResponseEntity<Boolean> result = template.exchange(BASE_URL + UPDATE_TASK_URL, HttpMethod.PUT,entity,Boolean.class);
		if (result != null && result.getStatusCode() != null
				&& result.getStatusCode().toString().equalsIgnoreCase("200")) {
			
			assertTrue(result.getBody());
			
		}
       
		
        
       
	}
	
	
	
	
	@Test
	public void test_create_task_Template() throws JSONException {
		
		//System.out.println("Token in this method is " + test_token);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + test_token);
		
		TaskTemplateTO request =new TaskTemplateTO();
		request.setTaskTemplateId(null);
		request.setName("Task Template Description");
		request.setTaskPriority("High");
		request.setDescription("Resolution");
		request.setAssigneeIds("pavan");
		request.setEstimatedDuration(72);
		request.setIsRecurring("YES");
		request.setRecurringPeriodCronExpression("*/10 * * * * *");
		request.setReportedId("pavan");
	
		RestTemplate template=new RestTemplate();
		
		HttpEntity<TaskTemplateTO> entity = new HttpEntity<TaskTemplateTO>(request, headers);
       
		ResponseEntity<Boolean> result = template.exchange(BASE_URL + CREATE_TASK_TEMPLATE_URL, HttpMethod.POST,entity,Boolean.class);
		if (result != null && result.getStatusCode() != null
				&& result.getStatusCode().toString().equalsIgnoreCase("200")) {
			
			assertTrue(result.getBody());
			
		}
       
		
        
       
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
