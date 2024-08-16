package com.goldeng.service;

import com.goldeng.dto.ParcelDTO;

public interface IParcelService {
    
    ParcelDTO createParcel(ParcelDTO parcelDTO);

    ParcelDTO getParcelById(Long parcelId);
}
