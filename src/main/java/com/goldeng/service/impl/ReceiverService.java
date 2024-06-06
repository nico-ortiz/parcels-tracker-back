package com.goldeng.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.ReceiverDTO;
import com.goldeng.dto.ReceiverDTOWithCommissions;
import com.goldeng.mapper.ReceiverMapper;
import com.goldeng.model.Receiver;
import com.goldeng.repository.ReceiverRepository;
import com.goldeng.service.IReceiverService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ReceiverService implements IReceiverService {

    @Autowired
    private ReceiverRepository receiverRepository;

    private ReceiverMapper receiverMapper;

    @Override
    public ReceiverDTO createReceiver(ReceiverDTO receiverDTO) {
        Receiver receiver = receiverMapper.receiverDTOToReceiver(receiverDTO);
        ReceiverDTO receiverDTOSaved = receiverMapper.receiverToReceiverDTO(receiverRepository.save(receiver));
        return receiverDTOSaved;
    }

    @Override
    public ReceiverDTO getReceiver(Long receiverId) {
        Optional<Receiver> receiver = receiverRepository.findById(receiverId);

        if (receiver.isPresent()) {
            return receiverMapper.receiverToReceiverDTO(receiver.get());
        }

        return new ReceiverDTO();
    }

    @Override
    public ReceiverDTO deleteReceiver(Long receiverId) {
        ReceiverDTO receiverToDelete = this.getReceiver(receiverId);

        if (receiverToDelete.getReceiverId() != null) {
            receiverRepository.delete(receiverMapper.receiverDTOToReceiver(receiverToDelete));
            return receiverToDelete;
        }
        
        return new ReceiverDTO();
    }

    @Override
    public ReceiverDTO updateReceiver(Long receiverId, ReceiverDTO receiverDTO) {
        ReceiverDTO receiverToUpdate = this.getReceiver(receiverId);

        if (receiverToUpdate.getReceiverId() == null) {
            return new ReceiverDTO();
        }

        receiverToUpdate.setFirstName(receiverDTO.getFirstName());
        receiverToUpdate.setLastName(receiverDTO.getLastName());
        receiverToUpdate.setAddress(receiverDTO.getAddress());
        receiverToUpdate.setPhoneNumber(receiverDTO.getPhoneNumber());
        receiverToUpdate.setDate(receiverDTO.getDate());
        receiverToUpdate.setOpeningHour(receiverDTO.getOpeningHour());
        receiverToUpdate.setClosingHour(receiverDTO.getClosingHour());
        return this.createReceiver(receiverDTO);
    }

    @Override
    public ReceiverDTOWithCommissions getReceiverWithReceivedCommissions(Long receiverId) {
        Optional<Receiver> receiver =  receiverRepository.findById(receiverId);

        if (!receiver.isPresent()) {
            return new ReceiverDTOWithCommissions();
        }

        return receiverMapper.receiverToReceiverDTOWC(receiver.get());
    }
}
