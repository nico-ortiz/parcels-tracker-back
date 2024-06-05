package com.goldeng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.PackageDTO;
import com.goldeng.model.Package;

@Mapper(
    componentModel = "spring",
    uses = {CustomerMapper.class, ReceiverMapper.class})
public interface PackageMapper {
    
    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);

    @Mapping(target = "commission.packages", ignore = true)
    @Mapping(target = "commission.commissionId", source = "commissionId")
    Package packageDTOToPackage(PackageDTO packageDTO);

    @Mapping(target = "commissionId", source = "commission.commissionId")
    PackageDTO packageToPackageDTO(Package package1);
}
