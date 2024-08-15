package com.goldeng.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.PackageDTO;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.model.Package;

@Mapper(
    componentModel = "spring",
    uses = {CustomerMapper.class, ReceiverMapper.class})
public interface PackageMapper {
    
    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);

    @Mapping(target = "commission.packages", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "commission.commissionId", source = "commissionId")
    Package packageDTOToPackage(PackageDTO packageDTO);

    @Mapping(target = "commissionId", source = "commission.commissionId")
    PackageDTO packageToPackageDTO(Package package1);

    PackageDTOWithoutCommission packageToPackageDTOWithoutCommissionId(Package package1);

    @Mapping(target = "commission", ignore = true)
    @Mapping(target = "price", ignore = true)
    Package packageDTOWCToPackage(PackageDTOWithoutCommission packageDTOWithoutCommission);

    List<PackageDTOWithoutCommission> packageListToPackageDTOWCList(List<Package> packages);
}
