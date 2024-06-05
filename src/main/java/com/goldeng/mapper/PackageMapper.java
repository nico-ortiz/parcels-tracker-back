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
    Package packageDTOToPackage(PackageDTO packageDTO);

    PackageDTO packageToPackageDTO(Package package1);
}
