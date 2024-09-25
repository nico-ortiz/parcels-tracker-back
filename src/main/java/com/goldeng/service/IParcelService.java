package com.goldeng.service;

import com.goldeng.dto.ParcelDTO;
import com.goldeng.model.enums.PackageType;

public interface IParcelService {
    
    ParcelDTO createParcel(ParcelDTO parcelDTO);

    ParcelDTO getParcelById(Long parcelId);

    ParcelDTO deleteParcelById(Long parcelId);

    ParcelDTO updateParcelById(Long parcelId, String description, PackageType packageType);
}
