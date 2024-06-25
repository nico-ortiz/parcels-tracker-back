package com.goldeng.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.goldeng.dataProvider.ReceiverData;
import com.goldeng.dto.ReceiverDTO;
import com.goldeng.dto.ReceiverDTOWithCommissions;
import com.goldeng.service.impl.ReceiverService;

@WebMvcTest(controllers = ReceiverController.class)
public class ReceiverControllerTest {
    
    @MockBean
    private ReceiverService receiverService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createReceiverTest() throws Exception {
        ReceiverDTO receiverToSave = ReceiverData.receiverDTOMock();
        
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        String requestBody = om.writeValueAsString(receiverToSave);
        
        when(this.receiverService.createReceiver(any(ReceiverDTO.class))).thenReturn(ReceiverData.receiverDTOMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/receivers/create", requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        verify(this.receiverService, times(1))
            .createReceiver(any(ReceiverDTO.class));
    }

    @Test
    void getReceiverTest() throws Exception {
        Long receiverId = 1L;
        
        when(this.receiverService.getReceiver(receiverId)).thenReturn(ReceiverData.receiverDTOMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/receivers/{receiverId}", receiverId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Sergio"));
    }

    @Test
    void whenReceiverIdNotExistsNotGetReceiverTest() throws Exception {
        Long receiverId = 1333L;
        
        when(this.receiverService.getReceiver(receiverId)).thenReturn(new ReceiverDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/receivers/{receiverId}", receiverId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteReceiverTest() throws Exception {
        Long receiverId = 1L;
        
        when(this.receiverService.deleteReceiver(receiverId)).thenReturn(ReceiverData.receiverDTOMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/receivers/del/{receiverId}", receiverId))
        .andExpect(MockMvcResultMatchers.status().isAccepted())
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Italia 34"));
    }

    @Test
    void whenReceiverIdNotExistsNotDeleteReceiverTest() throws Exception {
        Long receiverId = 11121L;
        
        when(this.receiverService.deleteReceiver(receiverId)).thenReturn(new ReceiverDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/receivers/del/{receiverId}", receiverId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateReceiverTest() throws Exception {
        Long receiverId = 1L;
        ReceiverDTO newData = new ReceiverDTO(
            1L,
            "Sergio",
            "Ramos",
            "Francia 104",
            "45612033",
            LocalDate.of(2025, 12, 22),
            LocalTime.of(16, 30),
            LocalTime.of(20, 30)
        );
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        String requestBody = om.writeValueAsString(newData);

        when(this.receiverService.updateReceiver(receiverId, newData)).thenReturn(ReceiverData.receiverDTOUpdatedMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/receivers/update/{receiverId}", receiverId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Francia 104"));
    }

    @Test
    void whenReceiverIdNotExistsNotUpdateReceiverTest() throws Exception {
        Long receiverId = 13333L;
        ReceiverDTO newData = new ReceiverDTO(
            1L,
            "Sergio",
            "Ramos",
            "Francia 104",
            "45612033",
            LocalDate.of(2025, 12, 22),
            LocalTime.of(16, 30),
            LocalTime.of(20, 30)
        );
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        String requestBody = om.writeValueAsString(newData);

        when(this.receiverService.updateReceiver(receiverId, newData)).thenReturn(new ReceiverDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/receivers/update/{receiverId}", receiverId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getReceiverWithReceivedCommissionsTest() throws Exception {
        Long receiverId = 1L;

        when(this.receiverService.getReceiverWithReceivedCommissions(receiverId)).thenReturn(ReceiverData.receiverDTOWithCommissionsMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/receivers/withComm/{receiverId}", receiverId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.receivedCommissions[0].packages.size()").value(2));
    }

    @Test
    void whenReceiverIdNotExistsNotGetReceiverWithReceivedCommissionsTest() throws Exception {
        Long receiverId = 1L;

        when(this.receiverService.getReceiverWithReceivedCommissions(receiverId)).thenReturn(new ReceiverDTOWithCommissions());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/receivers/withComm/{receiverId}", receiverId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
