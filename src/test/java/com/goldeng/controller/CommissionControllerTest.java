package com.goldeng.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

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
import com.goldeng.dataProvider.CommissionData;
import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.CommissionDTORequest;
import com.goldeng.service.ICommissionService;

@WebMvcTest(controllers = CommissionController.class)
public class CommissionControllerTest {
    
    @MockBean
    private ICommissionService commissionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCommissionTest() throws Exception {
        CommissionDTO commissionToSave = CommissionData.commissionDTOMock();
        when(this.commissionService.createCommission(any(CommissionDTORequest.class))).thenReturn(commissionToSave);

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        String requestBody = om.writeValueAsString(commissionToSave);
    
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/commissions/create")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
        )
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Comision con 3 sobres"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.packages.size()").value(3));

        verify(this.commissionService, times(1))
            .createCommission(any(CommissionDTORequest.class));
    }

    @Test
    void whenCustomerIdOrReceiverIdNotExistsNotCreateCommissionTest() throws Exception {
        CommissionDTO commissionToSave = CommissionData.commissionDTOMock();
        when(this.commissionService.createCommission(any(CommissionDTORequest.class))).thenReturn(new CommissionDTO());

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        String requestBody = om.writeValueAsString(commissionToSave);
    
        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/commissions/create")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(this.commissionService, times(1))
            .createCommission(any(CommissionDTORequest.class));
    }

    @Test
    void getCommissionTest() throws Exception {
        Long commissionId = 23L;

        when(this.commissionService.getCommission(anyLong())).thenReturn(CommissionData.commissionDTOMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/commissions/{commissionId}", commissionId)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(6000f))
        .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(1L));
        
        verify(this.commissionService, times(1))
            .getCommission(anyLong());
    }

    @Test
    void whenCommissionIdNotExistsNotGetCommissionTest() throws Exception {
        Long commissionId = 1123L;

        when(this.commissionService.getCommission(anyLong())).thenReturn(new CommissionDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/commissions/{commissionId}", commissionId)
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
        verify(this.commissionService, times(1))
            .getCommission(anyLong());
    }

    @Test
    void deleteCommissionTest() throws Exception {
        Long commissionId = 23L;

        when(this.commissionService.deleteCommission(anyLong())).thenReturn(CommissionData.commissionDTOMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/commissions/del/{commissionid}", commissionId)
        )
        .andExpect(MockMvcResultMatchers.status().isAccepted())
        .andExpect(MockMvcResultMatchers.jsonPath("$.commissionId").value(commissionId));

        verify(this.commissionService, times(1))
            .deleteCommission(anyLong());
    }

    @Test
    void whenCommissionIdNotExistsNotDeleteCommissionTest() throws Exception {
        Long commissionId = 22223L;

        when(this.commissionService.deleteCommission(anyLong())).thenReturn(new CommissionDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/commissions/del/{commissionid}", commissionId)
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(this.commissionService, times(1))
            .deleteCommission(anyLong());
    }

    @Test
    void updateCommissionTest() throws Exception {
        Long commissionId = 23L;
        CommissionDTO commissionDTOToUpdate = CommissionData.commissionUpdateDTOMock();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        String requestBody = om.writeValueAsString(commissionDTOToUpdate);

        when(this.commissionService.updateCommission(anyLong(), any(CommissionDTO.class))).thenReturn(commissionDTOToUpdate);

        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/commissions/update/{commissionId}", commissionId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.packages.size()").value(2));

        verify(this.commissionService, times(1))
            .updateCommission(anyLong(), any(CommissionDTO.class));
    }

    @Test
    void whenCommissionIdNotExistsNotUpdateCommissionTest() throws Exception {
        Long commissionId = 3323L;
        CommissionDTO commissionDTOToUpdate = CommissionData.commissionUpdateDTOMock();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        String requestBody = om.writeValueAsString(commissionDTOToUpdate);

        when(this.commissionService.updateCommission(anyLong(), any(CommissionDTO.class))).thenReturn(new CommissionDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/commissions/update/{commissionId}", commissionId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(this.commissionService, times(1))
            .updateCommission(anyLong(), any(CommissionDTO.class));
    }

    @Test
    void getCommissionsByDateTest() throws Exception {
        LocalDate date = LocalDate.of(2024, 8, 23);

        when(this.commissionService.getCommissionsByDate(date)).thenReturn(CommissionData.commissionDTOListMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/commissions/by-date?date={date}", date)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        verify(this.commissionService, times(1))
            .getCommissionsByDate(any(LocalDate.class));
    }

    @Test
    void whenNotExistsCommissionAtDateTest() throws Exception {
        LocalDate date = LocalDate.of(2026, 10, 2);

        when(this.commissionService.getCommissionsByDate(date)).thenReturn(new ArrayList<>());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/commissions/by-date?date={date}", date)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());

        verify(this.commissionService, times(1))
            .getCommissionsByDate(any(LocalDate.class));
    }

    @Test
    void getPackagesOfCommissionTest() throws Exception {
        Long commissionId = 23L;

        when(this.commissionService.getPackagesByCommission(anyLong())).thenReturn(CommissionData.commissionDTOMock().getPackages());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/commissions/{commissionId}/packages", commissionId)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        verify(this.commissionService, times(1))
            .getPackagesByCommission(anyLong());
    }

    @Test
    void whenNotExistsCommissionIdNotGetPackagesOfCommissionTest() throws Exception {
        Long commissionId = 2333L;

        when(this.commissionService.getPackagesByCommission(commissionId)).thenReturn(null);

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/commissions/{commissionId/packages}", commissionId)
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
