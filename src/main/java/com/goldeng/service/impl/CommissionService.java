package com.goldeng.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.dto.ReceiverDTO;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReceiverService receiverService;

    @Override
    public CommissionDTO createCommission(CommissionDTO commissionDTO) {
        Commission commission = commissionMapper.commissionDTOToCommission(commissionDTO);
        CustomerDTO customer  = customerService.getCustomer(commission.getCustomer().getPersonId());
        ReceiverDTO receiver  = receiverService.getReceiver(commission.getReceiver().getPersonId());

        if (customer.getCustomerId() == null || receiver.getReceiverId() == null) {
            return new CommissionDTO();
        }

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

        Commission commissionUpdated = commissionRepository.save(commissionMapper.commissionDTOToCommission(commissionToUpdate));
        return commissionMapper.commissionToCommissionDTO(commissionUpdated);
    }

    @Override
    public List<CommissionDTO> getCommissionsByDate(LocalDate date) {
        List<CommissionDTO> commissions = commissionMapper.commissionsListToCommissionsDTOList(commissionRepository.findCommissionsByDate(date));
        return commissions;
    }

    @Override
    public List<PackageDTOWithoutCommission> getPackagesByCommission(Long commissionId) {
        CommissionDTO commission = this.getCommission(commissionId);

        if (commission.getCommissionId() == null) {
            return null;
        }

        return commission.getPackages();
    }
    
}
