package com.goldeng.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldeng.dataProvider.CustomerData;
import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.CustomerDTOWithCommissions;
import com.goldeng.service.impl.CustomerService;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void createCustomerControllerTest() throws Exception {
        when(this.customerService.createCustomer(any(CustomerDTO.class))).thenReturn(CustomerData.newCustomerDTOMock());

        CustomerDTO customerToSave = CustomerData.customerDTOMock();
        String requestBody = new ObjectMapper().writeValueAsString(customerToSave);

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/customers/register")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        verify(this.customerService, times(1))
            .createCustomer(any(CustomerDTO.class));
    }

    @Test
    void getCustomersControllerTest() throws Exception {
        when(this.customerService.getCustomers()).thenReturn(CustomerData.customerDTOListMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/customers"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$[2].firstName").value("Paulo"));
    }

    @Test
    void getCustomerByIdControllerTest() throws Exception {
        Long customerId = 1L;
        when(this.customerService.getCustomer(customerId)).thenReturn(CustomerData.customerDTOMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/customers/{customerId}", customerId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Ramon"));
    }

    @Test
    void whenCustomerIdNotExistsGetCustomerByIdControllerTest() throws Exception {
        Long customerId = 189L;
        when(this.customerService.getCustomer(customerId)).thenReturn(new CustomerDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/customers/{customerId}", customerId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteCustomerControllerTest() throws Exception {
        Long customerId = 1L;
        when(this.customerService.deleteCustomer(customerId)).thenReturn(CustomerData.customerDTOMock());
        
        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/customers/del/{customerId}", customerId))
        .andExpect(MockMvcResultMatchers.status().isAccepted())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(customerId));
    }

    @Test
    void whenCustomerIdNotExistsNotDeleteCustomerControllerTest() throws Exception {
        Long customerId = 122L;
        when(this.customerService.deleteCustomer(customerId)).thenReturn(new CustomerDTO());
        
        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/customers/del/{customerId}", customerId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateCustomerControllerTest() throws Exception {
        Long customerId = 1L;
        CustomerDTO newCustomerData = new CustomerDTO(1L, "Ramon", "Sinatra", "Buenos Aires 21", "3584333123", "12123123", "rsinatra@gmail.com", "12121231231", "Sin4tr4.");    
        String requestBody = new ObjectMapper().writeValueAsString(newCustomerData);

        when(this.customerService.updateCustomer(customerId, newCustomerData)).thenReturn(CustomerData.customerDTOUpdatedMock());
    
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/customers/update/{customerId}", customerId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("rsinatra@gmail.com"));
    }   

    @Test
    void whenCustomerIdNotExistsNotUpdateCustomerControllerTest() throws Exception {
        Long customerId = 122L;
        CustomerDTO newCustomerData = new CustomerDTO(1L, "Ramon", "Sinatra", "Buenos Aires 21", "3584333123", "12123123", "rsinatra@gmail.com", "12121231231", "Sin4tr4.");    
        String requestBody = new ObjectMapper().writeValueAsString(newCustomerData);

        when(this.customerService.updateCustomer(customerId, newCustomerData)).thenReturn(new CustomerDTO());
    
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/customers/update/{customerId}", customerId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    } 

    @Test
    void getCustomerWithCommissionsControllerTest() throws Exception {
        Long customerId = 1L;
        when(this.customerService.getCustomerWithCommissions(customerId)).thenReturn(CustomerData.customerDTOWithCommissionsMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/customers/withComm/{customerId}", customerId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.commissions.length()").value(1));
    }

    @Test
    void whenCustomerIdNotExistsNotGetCustomerWithCommissionsControllerTest() throws Exception {
        Long customerId = 1111L;
        when(this.customerService.getCustomerWithCommissions(customerId)).thenReturn(new CustomerDTOWithCommissions());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/customers/withComm/{customerId}", customerId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getCustomerCommissionsControllerTest() throws Exception {
        Long customerId = 1L;

        when(this.customerService.getCustomerCommissions(customerId)).thenReturn(CustomerData.customerDTOWithCommissionsMock().getCommissions());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/customers/{customerId}/commissions", customerId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].packages.size()").value(2));
    }

    @Test
    void whenCustomerIdNotExistsNotGetCustomerCommissionsControllerTest() throws Exception {
        Long customerId = 1121L;

        when(this.customerService.getCustomerCommissions(customerId)).thenReturn(null);

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/customers/{customerId}/commissions", customerId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}