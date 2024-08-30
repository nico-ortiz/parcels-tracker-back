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
    @Mapping(target = "packageId", source = "parcelId")
    @Mapping(target = "commission.commissionId", source = "commissionId")
    Parcel parcelDTOToParcel(ParcelDTO parcelDTO);

    @Mapping(target = "commissionId", source = "commission.commissionId")
    @Mapping(target = "parcelId", source = "packageId")
    @Mapping(target = "packageId", source = "packageId")
    ParcelDTO parcelToParcelDTO(Parcel parcel);

    @Mapping(target = "parcelId", source = "packageId")
    ParcelDTOWithoutCommission parcelToParcelDTOWTC(Parcel parcel);
}
