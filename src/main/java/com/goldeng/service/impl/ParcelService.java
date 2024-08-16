package com.goldeng.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.ParcelDTO;
import com.goldeng.mapper.ParcelMapper;
import com.goldeng.model.packageSubClasses.Parcel;
import com.goldeng.repository.ParcelRepository;
import com.goldeng.service.IParcelService;
import com.goldeng.validator.ObjectsValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ParcelService implements IParcelService {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private CommissionService commissionService;

    private ObjectsValidator<ParcelDTO> parcelValidator;

    private ParcelMapper parcelMapper;

    @Override
    public ParcelDTO createParcel(ParcelDTO parcelDTO) {
        parcelValidator.validate(parcelDTO);

        CommissionDTO isCommissionExists = commissionService.getCommission(parcelDTO.getCommissionId());

        if (isCommissionExists.getCommissionId() == null) {
            return new ParcelDTO();
        }

        Parcel parcelSaved = parcelRepository.save(parcelMapper.parcelDTOToParcel(parcelDTO));
        return parcelMapper.parcelToParcelDTO(parcelSaved); 
    }

    @Override
    public ParcelDTO getParcelById(Long parcelId) {
        Optional<Parcel> existsParcel = parcelRepository.findById(parcelId);

        if (!existsParcel.isPresent()) {
            return new ParcelDTO();
        }

        return parcelMapper.parcelToParcelDTO(existsParcel.get());
    }
    
}
