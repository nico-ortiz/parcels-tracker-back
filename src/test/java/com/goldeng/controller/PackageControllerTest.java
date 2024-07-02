package com.goldeng.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import com.goldeng.dataProvider.PackageData;
import com.goldeng.dto.PackageDTO;
import com.goldeng.service.impl.PackageService;

@WebMvcTest(controllers = PackageController.class)
public class PackageControllerTest {
    
    @MockBean
    private PackageService packageService;

    @Autowired 
    private MockMvc mockMvc;

    @Test
    void createPackageTest() throws Exception {
        PackageDTO packageToSave = PackageData.packageDTOMock();
        String requestBody = new ObjectMapper().writeValueAsString(packageToSave);

        when(this.packageService.createPackage(any(PackageDTO.class))).thenReturn(packageToSave);

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/packages/create")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isAccepted())
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Caja con repuestos de auto"));
    }

    @Test
    void whenCommissionNotExistsNotCreatePackageTest() throws Exception {
        PackageDTO packageToSave = PackageData.packageDTOMock();
        String requestBody = new ObjectMapper().writeValueAsString(packageToSave);

        when(this.packageService.createPackage(any(PackageDTO.class))).thenReturn(new PackageDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.post("/packages/create")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deletePackageTest() throws Exception {
        Long packageId = 1L;

        when(this.packageService.deletePackage(anyLong())).thenReturn(PackageData.packageDTOMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/packages/del/{packageId}", packageId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.packageId").value(1L));
    }

    @Test
    void whenPackageIdNotExistsNotDeletePackageTest() throws Exception {
        Long packageId = 3331L;

        when(this.packageService.deletePackage(anyLong())).thenReturn(new PackageDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.delete("/packages/del/{packageId}", packageId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updatePackageTest() throws Exception {
        Long packageId = 1L;
        PackageDTO newData = PackageData.newpackageDTOMock();
        String requestBody = new ObjectMapper().writeValueAsString(newData);

        when(this.packageService.updatePackageDTO(packageId, newData)).thenReturn(newData);

        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/packages/update/{packageId}", packageId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Se modifico la caja a una caja mas grande con respuestos"));
    }

    @Test
    void whenPackageIdNotExistsNotUpdatePackageTest() throws Exception {
        Long packageId = 1L;
        PackageDTO newData = PackageData.newpackageDTOMock();
        String requestBody = new ObjectMapper().writeValueAsString(newData);

        when(this.packageService.updatePackageDTO(packageId, newData)).thenReturn(new PackageDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.put("/packages/update/{packageId}", packageId)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .content(requestBody))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getPackageTest() throws Exception {
        Long packageId = 1L;

        when(this.packageService.getPackageById(anyLong())).thenReturn(PackageData.packageDTOMock());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/packages/{packageId}", packageId))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.packageType").value("CAJA_MEDIANA"));
    }

    @Test
    void whenPackageIdNotExistsNotGetPackageTest() throws Exception {
        Long packageId = 9143L;

        when(this.packageService.getPackageById(anyLong())).thenReturn(new PackageDTO());

        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/packages/{packageId}", packageId))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
