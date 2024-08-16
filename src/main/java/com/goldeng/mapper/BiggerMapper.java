package com.goldeng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.BiggerDTO;
import com.goldeng.model.packageSubClasses.Bigger;

@Mapper(
    componentModel = "spring"
)
public interface BiggerMapper {
    
    BiggerMapper INSTANCE = Mappers.getMapper(BiggerMapper.class);

    @Mapping(target = "commission.commissionId", source = "commissionId")
    Bigger biggerDTOToBigger(BiggerDTO biggerDTO);

    @Mapping(target = "biggerId", source = "packageId")
    @Mapping(target = "commissionId", source = "commission.commissionId")
    BiggerDTO biggerToBiggerDTO(Bigger bigger);
}
