package com.goldeng.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.goldeng.dataProvider.PackageData;
import com.goldeng.dto.PackageDTO;
import com.goldeng.model.Package;
import com.goldeng.model.enums.PackageType;
import com.goldeng.mapper.PackageMapper;
import com.goldeng.repository.PackageRepository;
import com.goldeng.service.impl.CommissionService;
import com.goldeng.service.impl.PackageService;
import com.goldeng.validator.ObjectsValidator;

@ExtendWith(MockitoExtension.class)
public class PackageServiceTest {
    
    @Mock
    private PackageRepository packageRepository;

    @Mock
    private PackageMapper packageMapper;

    @Mock
    private CommissionService commissionService;

    @Mock
    private ObjectsValidator<PackageDTO> packageValidator;

    @InjectMocks
    private PackageService packageService;

    @Test
    void createPackageTest() {
        //Given
        PackageDTO packageDTO = PackageData.packageDTOMock();

        //When
        doNothing().when(packageValidator).validate(packageDTO);
        when(this.commissionService.getCommission(anyLong())).thenReturn(PackageData.getCommissionDTOMock());
        when(this.packageMapper.packageDTOToPackage(any(PackageDTO.class))).thenReturn(PackageData.packageMock());
        when(this.packageRepository.save(any(Package.class))).thenReturn(PackageData.packageMock());
        when(this.packageMapper.packageToPackageDTO(any(Package.class))).thenReturn(packageDTO);


        @SuppressWarnings("unused")
        PackageDTO packageSaved = this.packageService.createPackage(packageDTO);

        //Then
        ArgumentCaptor<Package> argumentCaptor = ArgumentCaptor.forClass(Package.class);
        verify(this.packageRepository).save(argumentCaptor.capture());
        assertEquals("Caja con repuestos de auto", argumentCaptor.getValue().getDescription());
    }

    @Test
    void deletePackageTest() {
        //Given 
        Long packageId = 1L;

        //When
        when(this.packageRepository.findById(packageId)).thenReturn(Optional.of(PackageData.packageMock()));
        when(this.packageMapper.packageToPackageDTO(any(Package.class))).thenReturn(PackageData.packageDTOMock());
        when(this.packageMapper.packageDTOToPackage(any(PackageDTO.class))).thenReturn(PackageData.packageMock());
        doNothing().when(this.packageRepository).delete(any(Package.class));

        PackageDTO packageDeleted = this.packageService.deletePackage(packageId);

        //Then
        assertNotNull(packageDeleted.getPackageId());
        assertEquals(PackageType.CAJA_MEDIANA, packageDeleted.getPackageType());
        verify(this.packageRepository).delete(any(Package.class));
    }

    @Test
    void updatePackageTest() {
        //Given
        Long packageId = 1L;
        PackageDTO newData = PackageData.newpackageDTOMock();

        //When
        when(this.packageRepository.findById(anyLong())).thenReturn(Optional.of(PackageData.packageMock()));
        when(this.packageMapper.packageToPackageDTO(any(Package.class))).thenReturn(PackageData.packageDTOMock());
        doNothing().when(packageValidator).validate(newData);
        when(this.commissionService.getCommission(anyLong())).thenReturn(PackageData.getCommissionDTOMock());
        when(this.packageMapper.packageDTOToPackage(any(PackageDTO.class))).thenReturn(PackageData.packageMock());
        when(this.packageRepository.save(any(Package.class))).thenReturn(PackageData.newpackageMock());

        PackageDTO packageUpdated = this.packageService.updatePackageDTO(packageId, newData);
    
        //Then
        assertNotNull(packageUpdated);
        assertEquals("Se modifico la caja a una caja mas grande con respuestos", packageUpdated.getDescription());
        verify(this.packageRepository).save(any(Package.class));   
    }

    @Test
    void getPackageByIdTest() {
        //Given
        Long packageId = 1L;

        //When
        when(this.packageRepository.findById(packageId)).thenReturn(Optional.of(PackageData.packageMock()));
        when(this.packageMapper.packageToPackageDTO(any(Package.class))).thenReturn(PackageData.packageDTOMock());

        PackageDTO packageDTO = this.packageService.getPackageById(packageId);

        //Then
        assertNotNull(packageDTO);
        assertEquals(1L, packageDTO.getPackageId());
        verify(this.packageRepository).findById(anyLong());
    }


}