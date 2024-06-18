package com.goldeng.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.goldeng.dataProvider.CommissionData;
import com.goldeng.dataProvider.CustomerData;
import com.goldeng.dataProvider.ReceiverData;
import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.CommissionDTORequest;
import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.dto.ReceiverDTO;
import com.goldeng.mapper.CommissionMapper;
import com.goldeng.model.Commission;
import com.goldeng.repository.CommissionRepository;
import com.goldeng.service.impl.CommissionService;
import com.goldeng.service.impl.CustomerService;
import com.goldeng.service.impl.ReceiverService;
import com.goldeng.validator.ObjectsValidator;

@ExtendWith(MockitoExtension.class)
public class CommissionServiceTest {
    
    @Mock
    private CommissionRepository commissionRepository;

    @Mock
    private CommissionMapper commissionMapper;

    @Mock
    private CustomerService customerService;

    @Mock
    private ReceiverService receiverService;

    @Mock
    private ObjectsValidator<CommissionDTORequest> commissionDTORequestValidator;

    @Mock
    private ObjectsValidator<CommissionDTO> commissionDTOValidator;

    @InjectMocks
    CommissionService commissionService;

    @Test
    void createCommissionTest() {
        //Given
        CommissionDTORequest commissionDTORequest = CommissionData.commissionDTORequestMock();

        //When
        when(this.commissionMapper.commissionDTORequestToCommission(any(CommissionDTORequest.class))).thenReturn(CommissionData.commissionMock());
        when(this.customerService.getCustomer(anyLong())).thenReturn(CustomerData.customerDTOMock());
        when(this.receiverService.getReceiver(anyLong())).thenReturn(ReceiverData.receiverDTOMock());
        when(this.commissionMapper.commissionToCommissionDTO(any(Commission.class))).thenReturn(CommissionData.commissionDTOMock());
        when(this.commissionRepository.save(any(Commission.class))).thenReturn(CommissionData.commissionMock());

        CommissionDTO commissionSaved = this.commissionService.createCommission(commissionDTORequest);

        //Then
        assertNotNull(commissionSaved);
        assertEquals(3, commissionSaved.getPackages().size());
        verify(this.commissionRepository).save(any(Commission.class));
    }

    @Test
    void whenCustomerNotExistsNotCreateCommissionTest() {
        //Given
        CommissionDTORequest commissionDTORequest = CommissionData.commissionDTORequestMock();

        //When
        when(this.commissionMapper.commissionDTORequestToCommission(any(CommissionDTORequest.class))).thenReturn(CommissionData.commissionMock());
        when(this.customerService.getCustomer(anyLong())).thenReturn(CustomerData.customerDTOMock());
        when(this.receiverService.getReceiver(anyLong())).thenReturn(new ReceiverDTO());

        CommissionDTO commissionSaved = this.commissionService.createCommission(commissionDTORequest);

        //Then
        assertNull(commissionSaved.getDescription());
        assertEquals(null, commissionSaved.getCustomerId());
    }

    @Test
    void whenReceiverNotExistsNotCreateCommissionTest() {
        //Given
        CommissionDTORequest commissionDTORequest = CommissionData.commissionDTORequestMock();

        //When
        when(this.commissionMapper.commissionDTORequestToCommission(any(CommissionDTORequest.class))).thenReturn(CommissionData.commissionMock());
        when(this.customerService.getCustomer(anyLong())).thenReturn(new CustomerDTO());

        CommissionDTO commissionSaved = this.commissionService.createCommission(commissionDTORequest);

        //Then
        assertNull(commissionSaved.getDescription());
        assertEquals(null, commissionSaved.getReceiverId());
    }

    @Test
    void getCommissionTest() {
        //Given
        Long commissionId = 23L;

        //When
        when(this.commissionRepository.findById(anyLong())).thenReturn(Optional.of(CommissionData.commissionMock()));
        when(this.commissionMapper.commissionToCommissionDTO(any(Commission.class))).thenReturn(CommissionData.commissionDTOMock());

        CommissionDTO commissionDTO = this.commissionService.getCommission(commissionId);

        //Then
        assertNotNull(commissionDTO);
        assertEquals(6000f, commissionDTO.getPrice());
        verify(this.commissionRepository).findById(anyLong());
    }

    @Test
    void whenNotExistsCommissionTest() {
        //Given
        Long commissionId = 43L;

        //When
        when(this.commissionRepository.findById(anyLong())).thenReturn(Optional.empty());

        CommissionDTO commissionDTO = this.commissionService.getCommission(commissionId);

        //Then
        assertNull(commissionDTO.getCommissionId());
        assertEquals(null, commissionDTO.getDescription());
    }

    @Test
    void deleteCommissionTest() {
        //Given
        Long commissionId = 23L;

        //When
        when(this.commissionRepository.findById(anyLong())).thenReturn(Optional.of(CommissionData.commissionMock()));
        when(this.commissionMapper.commissionToCommissionDTO(any(Commission.class))).thenReturn(CommissionData.commissionDTOMock());
        doNothing().when(this.commissionRepository).delete(any(Commission.class));
        when(this.commissionMapper.commissionDTOToCommission(any(CommissionDTO.class))).thenReturn(CommissionData.commissionMock());
        
        CommissionDTO commissionDeleted = this.commissionService.deleteCommission(commissionId);

        //Then
        assertNotNull(commissionDeleted);
        assertTrue(!commissionDeleted.getPackages().isEmpty());
        verify(this.commissionRepository).delete(any(Commission.class));
    }

    @Test
    void whenNotExistsCommissionIdNotDelete() {
        //Given
        Long commissionId = 123L;

        //When
        when(this.commissionRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        CommissionDTO commissionDeleted = this.commissionService.deleteCommission(commissionId);

        //Then
        assertNull(commissionDeleted.getCommissionId());
        assertTrue(commissionDeleted.getPackages() == null);
    }

    @Test
    void updateCommissionTest() {
        //Given
        Long commissionId = 22L;
        CommissionDTO newData = CommissionData.commissionUpdateDTOMock();

        //When
        when(this.commissionRepository.findById(anyLong())).thenReturn(Optional.of(CommissionData.commissionMock()));
        when(this.commissionMapper.commissionToCommissionDTO(any(Commission.class))).thenReturn(CommissionData.commissionDTOMock());
        when(this.commissionRepository.save(any(Commission.class))).thenReturn(CommissionData.commissionUpdatedMock());
        when(this.commissionMapper.commissionDTOToCommission(any(CommissionDTO.class))).thenReturn(CommissionData.commissionUpdatedMock());

        CommissionDTO commissionUpdated = this.commissionService.updateCommission(commissionId, newData);

        //Then
        assertNotNull(commissionUpdated);   
        assertEquals(2, commissionUpdated.getPackages().size());
        verify(this.commissionRepository).save(any(Commission.class));
    }

    @Test
    void whenNotExistsCommissionIdNotUpdateCommissionTest() {
        //Given
        Long commissionId = 222L;
        CommissionDTO newData = CommissionData.commissionUpdateDTOMock();

        //When
        when(this.commissionRepository.findById(anyLong())).thenReturn(Optional.empty());

        CommissionDTO commissionUpdated = this.commissionService.updateCommission(commissionId, newData);

        //Then
        assertNull(commissionUpdated.getCommissionId());   
        assertEquals(null, commissionUpdated.getPackages());
    }

    @Test
    void getCommissionsByDateTest() {
        //Given
        LocalDate date = LocalDate.of(2024, 8, 23);

        //When
        when(this.commissionRepository.findCommissionsByDate(date)).thenReturn(CommissionData.commissionListMock());
        when(this.commissionMapper.commissionsListToCommissionsDTOList(anyList())).thenReturn(CommissionData.commissionDTOListMock());

        List<CommissionDTO> commissions = this.commissionService.getCommissionsByDate(date);

        //Then
        assertNotNull(commissions);
        assertEquals(date, commissions.get(0).getDate());
        verify(this.commissionRepository).findCommissionsByDate(any(LocalDate.class));
    }

    @Test
    void getPackagesByCommissionTest() {
        //Given
        Long commissionId = 1L;

        //When
        when(this.commissionRepository.findById(anyLong())).thenReturn(Optional.of(CommissionData.commissionMock()));
        when(this.commissionMapper.commissionToCommissionDTO(any(Commission.class))).thenReturn(CommissionData.commissionDTOMock());

        List<PackageDTOWithoutCommission> packages = this.commissionService.getPackagesByCommission(commissionId);
        
        //Then
        assertNotNull(packages);
        assertEquals(3, packages.size());
        verify(this.commissionRepository).findById(anyLong());
    }

    @Test
    void whenNotExistsCommissionIdNotExistsPackagesTest() {
        //Given
        Long commissionId = 100L;

        //When
        when(this.commissionRepository.findById(anyLong())).thenReturn(Optional.empty());

        List<PackageDTOWithoutCommission> packages = this.commissionService.getPackagesByCommission(commissionId);
        
        //Then
        assertNull(packages);
    }
}
