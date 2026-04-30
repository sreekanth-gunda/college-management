package com.colleage.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.colleage.entity.GovtJuniorEntity;
import com.colleage.service.GovtJuniorService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GovtJuniorController.class)
class GovtJuniorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GovtJuniorService service;

    @Test
    void testGetAllStudents() throws Exception {
        when(service.getAllStudents()).thenReturn(List.of(new GovtJuniorEntity()));

        mockMvc.perform(get("/colleage/getAllStudents"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetById() throws Exception {
        when(service.getStudentById(1)).thenReturn(new GovtJuniorEntity());

        mockMvc.perform(get("/colleage/ById/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetByRequestParam() throws Exception {
        when(service.getStudentById(1)).thenReturn(new GovtJuniorEntity());

        mockMvc.perform(get("/colleage/ById?id=1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(service).delete(1);

        mockMvc.perform(delete("/colleage/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        when(service.update(eq(1), any())).thenReturn(new GovtJuniorEntity());

        mockMvc.perform(put("/colleage/update/1")
                .contentType("application/json")
                .content("{\"name\":\"Sree\",\"group\":\"MPC\",\"rollNo\":\"101\",\"campus\":\"Hyd\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateStudent() throws Exception {
        doNothing().when(service).createStudent(any());

        mockMvc.perform(post("/colleage/create")
                .contentType("application/json")
                .content("{\"name\":\"Sree\",\"group\":\"MPC\",\"rollNo\":\"101\",\"campus\":\"Hyd\"}"))
                .andExpect(status().isCreated());
    }
}