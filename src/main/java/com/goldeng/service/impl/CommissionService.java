package com.goldeng.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.mapper.CommissionMapper;
import com.goldeng.model.Commission;
import com.goldeng.model.enums.Status;
import com.goldeng.repository.CommissionRepository;
import com.goldeng.service.ICommissionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommissionService implements ICommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    private CommissionMapper commissionMapper;

    @Override
    public CommissionDTO createCommission(CommissionDTO commissionDTO) {
        Commission commission = commissionMapper.commissionDTOToCommission(commissionDTO);
        commission.setStatus(Status.EN_PREPARACION);
        commission.setDate(LocalDate.now());
        commission.setPrice(0f);
        CommissionDTO commissionSaved = commissionMapper.commissionToCommissionDTO(commissionRepository.save(commission));
        return commissionSaved;
    }

    @Override
    public CommissionDTO getCommission(Long commissionId) {
        Optional<Commission> commission = commissionRepository.findById(commissionId);

        if (commission.isPresent()) {
            return commissionMapper.commissionToCommissionDTO(commission.get());
        }

        return new CommissionDTO();
    }

    @Override
    public CommissionDTO deleteCommission(Long commissionId) {
        CommissionDTO commissionToDelete = this.getCommission(commissionId);
        
        if (commissionToDelete.getCommissionId() == null) {
            return new CommissionDTO();
        }

        commissionRepository.delete(commissionMapper.commissionDTOToCommission(commissionToDelete));
        return commissionToDelete;
    }

    @Override
    public CommissionDTO updateCommission(Long commissionId, CommissionDTO commissionDTO) {
        CommissionDTO commissionToUpdate = this.getCommission(commissionId);        

        if (commissionToUpdate.getCommissionId() == null) {
            return new CommissionDTO();
        }

        commissionToUpdate.setDescription(commissionDTO.getDescription());
        commissionToUpdate.setStatus(commissionDTO.getStatus());
        commissionToUpdate.setDate(commissionDTO.getDate());
        commissionToUpdate.setPrice(commissionDTO.getPrice());
        commissionToUpdate.setCustomerId(commissionDTO.getCustomerId());
        commissionToUpdate.setReceiverId(commissionDTO.getReceiverId());
        commissionToUpdate.setPackages(commissionDTO.getPackages());

        return this.createCommission(commissionToUpdate);
    }
    
}
