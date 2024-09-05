package com.goldeng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.ParcelDTO;
import com.goldeng.dto.ParcelDTOWithoutCommission;
import com.goldeng.model.packageSubClasses.Parcel;

@Mapper(
    componentModel = "spring"
)
public interface ParcelMapper {
    
    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    @Mapping(target = "price", ignore = true)
    @Mapping(target = "commission.commissionId", source = "commissionId")
    @Mapping(target = "packageId", source = "packageId")
    Parcel parcelDTOToParcel(ParcelDTO parcelDTO);

    @Mapping(target = "commissionId", source = "commission.commissionId")
    ParcelDTO parcelToParcelDTO(Parcel parcel);
    
    ParcelDTOWithoutCommission parcelToParcelDTOWTC(Parcel parcel);
}
