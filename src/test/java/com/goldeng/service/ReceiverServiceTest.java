package com.goldeng.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.goldeng.dataProvider.ReceiverData;
import com.goldeng.dto.ReceiverDTO;
import com.goldeng.dto.ReceiverDTOWithCommissions;
import com.goldeng.mapper.ReceiverMapper;
import com.goldeng.model.Receiver;
import com.goldeng.repository.ReceiverRepository;
import com.goldeng.service.impl.ReceiverService;
import com.goldeng.validator.ObjectsValidator;

@ExtendWith(MockitoExtension.class)
public class ReceiverServiceTest {
    
    @Mock
    private ReceiverRepository receiverRepository;

    @Mock
    private ReceiverMapper receiverMapper;

    @Mock
    private ObjectsValidator<ReceiverDTO> receiverValidator;

    @InjectMocks
    private ReceiverService receiverService; 

    @Test
    void createReceiverTest() {
        //Given
        ReceiverDTO receiverDTO = ReceiverData.receiverDTOMock();

        //When
        doNothing().when(this.receiverValidator).validate(receiverDTO);
        when(this.receiverMapper.receiverDTOToReceiver(any(ReceiverDTO.class))).thenReturn(ReceiverData.receiverMock());
        when(this.receiverMapper.receiverToReceiverDTO(any(Receiver.class))).thenReturn(receiverDTO);
        when(this.receiverRepository.save(any(Receiver.class))).thenReturn(ReceiverData.receiverMock());

        @SuppressWarnings("unused")
        ReceiverDTO receiverSaved = this.receiverService.createReceiver(receiverDTO);

        //Then
        ArgumentCaptor<Receiver> receiverArgumentCaptor = ArgumentCaptor.forClass(Receiver.class);
        verify(this.receiverRepository).save(receiverArgumentCaptor.capture());
        assertEquals(1L, receiverArgumentCaptor.getValue().getPersonId());
        assertEquals("Italia 34", receiverArgumentCaptor.getValue().getAddress());
    }

    @Test
    void getReceiverByIdTest() {
        //Given
        Long receiverId = 1L;

        //When
        when(this.receiverRepository.findById(anyLong())).thenReturn(Optional.of(ReceiverData.receiverMock()));
        when(this.receiverMapper.receiverToReceiverDTO(any(Receiver.class))).thenReturn(ReceiverData.receiverDTOMock());
        
        ReceiverDTO receiverDTO = this.receiverService.getReceiver(receiverId);

        //Then
        assertNotNull(receiverDTO);
        assertEquals(receiverId, receiverDTO.getReceiverId());
        verify(this.receiverRepository).findById(receiverId);
    }

    @Test
    void whenReceiverIdNotExistsReturnNullTest() {
        //Given 
        Long receiverId = 22L;

        //When
        when(this.receiverRepository.findById(anyLong())).thenReturn(Optional.empty());

        ReceiverDTO receiverDTO = this.receiverService.getReceiver(receiverId);

        //Then\
        assertNull(receiverDTO.getReceiverId());
        assertNull(receiverDTO.getLastName());
    }

    @Test
    void deleteReceiverTest() {
        //Given
        Long receiverId = 1L;

        //When
        when(this.receiverRepository.findById(anyLong())).thenReturn(Optional.of(ReceiverData.receiverMock()));
        when(this.receiverMapper.receiverToReceiverDTO(any(Receiver.class))).thenReturn(ReceiverData.receiverDTOMock());
        doNothing().when(this.receiverRepository).delete(any(Receiver.class));
        when(this.receiverMapper.receiverDTOToReceiver(any(ReceiverDTO.class))).thenReturn(ReceiverData.receiverMock());

        ReceiverDTO receiverDTO = this.receiverService.deleteReceiver(receiverId);

        //Then
        assertNotNull(receiverDTO);
        assertEquals(receiverId, receiverDTO.getReceiverId());
        verify(this.receiverRepository).delete(any(Receiver.class));
    }

    @Test
    void whenReceiverIdNotExistsNotDeleteTest() {
        //Given
        Long receiverId = 1L;

        //When
        when(this.receiverRepository.findById(anyLong())).thenReturn(Optional.empty());

        ReceiverDTO receiverDTO = this.receiverService.deleteReceiver(receiverId);

        //Then
        assertNull(receiverDTO.getFirstName());
        assertEquals(null, receiverDTO.getReceiverId());
    }

    @Test
    void updateReceiverTest() {
        //Given
        Long receiverId = 1L;
        ReceiverDTO newReceiverData =new ReceiverDTO(
            1L,
            "Sergio",
            "Ramos",
            "Sarmiento 1010",
            "45612033",
            LocalDate.of(2024, 12, 22),
            LocalTime.of(13, 30),
            LocalTime.of(17, 30)
        );

        //When
        doNothing().when(this.receiverValidator).validate(any(ReceiverDTO.class));
        when(this.receiverRepository.findById(anyLong())).thenReturn(Optional.of(ReceiverData.receiverMock()));
        when(this.receiverMapper.receiverToReceiverDTO(any(Receiver.class))).thenReturn(ReceiverData.receiverDTOMock());
        when(this.receiverMapper.receiverDTOToReceiver(any(ReceiverDTO.class))).thenReturn(ReceiverData.receiverMock());
        when(this.receiverRepository.save(any(Receiver.class))).thenReturn(ReceiverData.receiverMock());

        ReceiverDTO receiverUpdated = this.receiverService.updateReceiver(receiverId, newReceiverData);

        //Then
        assertNotNull(receiverUpdated);
        assertEquals("Sarmiento 1010", receiverUpdated.getAddress());
        verify(this.receiverRepository).save(any(Receiver.class));
    }

    @Test
    void whenReceiverIdNotExistsNotUpdateTest() {
        //Given
        Long receiverId = 1L;
        ReceiverDTO newReceiverData =new ReceiverDTO(
            1L,
            "Sergio",
            "Ramos",
            "Sarmiento 1010",
            "45612033",
            LocalDate.of(2024, 12, 22),
            LocalTime.of(13, 30),
            LocalTime.of(17, 30)
        );

        //When
        doNothing().when(this.receiverValidator).validate(any(ReceiverDTO.class));
        when(this.receiverRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        ReceiverDTO receiverUpdated = this.receiverService.updateReceiver(receiverId, newReceiverData);

        //Then
        assertNull(receiverUpdated.getReceiverId());
        assertEquals(null, receiverUpdated.getAddress());
    }

    @Test
    void getReceiverWithReceivedCommissionsTest() {
        //Given
        Long receiverId = 1L;

        //When
        when(this.receiverRepository.findById(anyLong())).thenReturn(Optional.of(ReceiverData.receiverMock()));
        when(this.receiverMapper.receiverToReceiverDTOWC(any(Receiver.class))).thenReturn(ReceiverData.receiverDTOWithCommissionsMock());

        ReceiverDTOWithCommissions receiver = this.receiverService.getReceiverWithReceivedCommissions(receiverId);

        //Then
        assertNotNull(receiver);            
        assertEquals("Marco", receiver.getFirstName());
        verify(this.receiverRepository).findById(anyLong());
    }

    @Test
    void whenNotExistsReceiverIdThenReturnsNullTest() {
        //Given
        Long receiverId = 1L;

        //When
        when(this.receiverRepository.findById(anyLong())).thenReturn(Optional.empty());

        ReceiverDTOWithCommissions receiver = this.receiverService.getReceiverWithReceivedCommissions(receiverId);

        //Then
        assertNull(receiver.getReceiverId());            
        assertEquals(null, receiver.getFirstName());
    }
}
