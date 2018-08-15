package com.personal.replenish.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

    private MockMvc mockMvc;

    String taskPayload = null;
    String taskPayload_error=null;
    String taskPayload_update=null;
    String taskTemplatePayload = null;

    @Mock
    public TaskController taskController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        try {
            taskPayload = new String(Files.readAllBytes(Paths.get("src/test/resources/taskPayload.json")));
            taskPayload_update = new String(Files.readAllBytes(Paths.get("src/test/resources/taskPayload_update.json")));
            taskPayload_error = new String(Files.readAllBytes(Paths.get("src/test/resources/taskPayload_error.json")));

            taskTemplatePayload = new String(Files.readAllBytes(Paths.get("src/test/resources/taskPayload.json")));
            taskTemplatePayload = new String(Files.readAllBytes(Paths.get("src/test/resources/taskPayload.json")));

            //fdreq2 = new String(Files.readAllBytes(Paths.get("src/test/resources/fundingrequest_2.json")));

            mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testcreatetask() throws Exception {
        mockMvc.perform(post("/replenisher/tasks/createTask")
                .contentType(MediaType.APPLICATION_JSON).content(taskPayload)).andExpect(status().isOk());
    }


    @Test
    public void testcreatetaskwithError() throws Exception {
        mockMvc.perform(post("/replenisher/tasks/createTask")
                .contentType(MediaType.APPLICATION_JSON).content(taskPayload_error)).andExpect(status().isBadRequest());
    }

    @Test
    public void testupdateTask() throws Exception {
        mockMvc.perform(put("/replenisher/tasks/updateTask")
                .contentType(MediaType.APPLICATION_JSON).content(taskPayload_update)).andExpect(status().isOk());
    }

    @Test
    public void testdeleteTask() throws Exception {
        mockMvc.perform(delete("/replenisher/tasks/deleteTask")
                .contentType(MediaType.APPLICATION_JSON).param("taskId","60")).andExpect(status().isOk());
    }


    @Test
    public void testgetAllTasksByAssigneeTask() throws Exception {
        mockMvc.perform(get("/replenisher/tasks/getAllTasksByAssignee/{userId}","2")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }



    @Test
    public void testgetAllTasksByReportedTask() throws Exception {
        mockMvc.perform(get("/replenisher/tasks/getAllTasksByReported/{userId}","2")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testgetAllTasks() throws Exception {
        mockMvc.perform(get("/replenisher/tasks/getAllTasks")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


}
