package com.goldeng.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.ParcelDTO;
import com.goldeng.mapper.ParcelMapper;
import com.goldeng.model.enums.PackageType;
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

    private static final double PRICE_PARCEL_SMALL = 10000;

    private static final double PRICE_PARCEL_MEDIUM = 13000;

    private static final double PRICE_PARCEL_BIG = 16000;

    @Override
    public ParcelDTO createParcel(ParcelDTO parcelDTO) {
        parcelValidator.validate(parcelDTO);

        CommissionDTO isCommissionExists = commissionService.getCommission(parcelDTO.getCommissionId());

        if (isCommissionExists.getCommissionId() == null) {
            return new ParcelDTO();
        }

        if (parcelDTO.getPackageType().equals(PackageType.CAJA_CHICA)) {
            parcelDTO.setPrice(PRICE_PARCEL_SMALL);
        } else if (parcelDTO.getPackageType().equals(PackageType.CAJA_MEDIANA)) {
            parcelDTO.setPrice(PRICE_PARCEL_MEDIUM);
        } else {
            parcelDTO.setPrice(PRICE_PARCEL_BIG);
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

    @Override
    public ParcelDTO deleteParcelById(Long parcelId) {
        ParcelDTO parcelDTO = this.getParcelById(parcelId);

        if (parcelDTO.getPackageId() == null) {
            return new ParcelDTO();
        }

        parcelRepository.delete(parcelMapper.parcelDTOToParcel(parcelDTO));
        return parcelDTO;
    }

    @Override
    public ParcelDTO updateParcelById(Long parcelId, String description, PackageType packageType) {
        Optional<Parcel> parcel = this.parcelRepository.findById(parcelId);

        if (!parcel.isPresent()) {
            return new ParcelDTO();
        }

        Parcel parcelUpdated = parcel.get();
        parcelUpdated.setDescription(description);
        parcelUpdated.setPackageType(packageType);

        return this.parcelMapper.parcelToParcelDTO(this.parcelRepository.save(parcelUpdated));
    }
    
}
