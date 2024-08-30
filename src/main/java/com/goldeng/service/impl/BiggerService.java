package com.goldeng.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.BiggerDTO;
import com.goldeng.dto.CommissionDTO;
import com.goldeng.mapper.BiggerMapper;
import com.goldeng.model.packageSubClasses.Bigger;
import com.goldeng.repository.BiggerRepository;
import com.goldeng.service.IBiggerService;
import com.goldeng.service.ICommissionService;
import com.goldeng.validator.ObjectsValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BiggerService implements IBiggerService {

    @Autowired
    private BiggerRepository biggerRepository;

    @Autowired
    private ICommissionService commissionService;

    private ObjectsValidator<BiggerDTO> biggerValidator;

    private BiggerMapper biggerMapper;

    @Override
    public BiggerDTO createBigger(BiggerDTO biggerDTO) {
        biggerValidator.validate(biggerDTO);

        CommissionDTO commission = commissionService.getCommission(biggerDTO.getCommissionId());

        if (commission.getCommissionId() == null) {
            return new BiggerDTO();
        }

        Bigger biggerSaved = biggerRepository.save(biggerMapper.biggerDTOToBigger(biggerDTO));
        return biggerMapper.biggerToBiggerDTO(biggerSaved);
    }

    @Override
    public BiggerDTO getBigger(Long biggerId) {
        Optional<Bigger> bigger = biggerRepository.findById(biggerId);

        if (!bigger.isPresent()) {
            return new BiggerDTO();
        }

        return biggerMapper.biggerToBiggerDTO(bigger.get());
    }

    @Override
    public BiggerDTO deleteBigger(Long biggerId) {
        BiggerDTO biggerDTO = this.getBigger(biggerId);

        if (biggerDTO == null) {
            return new BiggerDTO();
        }

        biggerRepository.delete(biggerMapper.biggerDTOToBigger(biggerDTO));
        return biggerDTO;
    }
    
}
