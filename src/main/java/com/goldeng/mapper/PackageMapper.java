package com.goldeng.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.BiggerDTO;
import com.goldeng.dto.BiggerDTOWithoutCommission;
import com.goldeng.dto.EnvelopeDTO;
import com.goldeng.dto.EnvelopeDTOWithoutCommission;
import com.goldeng.dto.PackageDTO;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.dto.ParcelDTO;
import com.goldeng.dto.ParcelDTOWithoutCommission;
import com.goldeng.model.Package;
import com.goldeng.model.packageSubClasses.Bigger;
import com.goldeng.model.packageSubClasses.Envelope;
import com.goldeng.model.packageSubClasses.Parcel;

@Mapper(
    componentModel = "spring",
    uses = {CustomerMapper.class, ReceiverMapper.class, EnvelopeMapper.class, ParcelMapper.class, BiggerMapper.class})
public interface PackageMapper {
    
    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);

    @Mapping(target = "commission.packages", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "commission.commissionId", source = "commissionId")
    Package packageDTOToPackage(PackageDTO packageDTO);

    @SubclassMapping(target = EnvelopeDTO.class, source = Envelope.class)
    @SubclassMapping(target = ParcelDTO.class, source = Parcel.class)
    @SubclassMapping(target = BiggerDTO.class, source = Bigger.class)
    @Mapping(target = "commissionId", source = "commission.commissionId")
    PackageDTO packageToPackageDTO(Package package1);

    @SubclassMapping(target = EnvelopeDTOWithoutCommission.class, source = Envelope.class)
    @SubclassMapping(target = ParcelDTOWithoutCommission.class, source = Parcel.class)
    @SubclassMapping(target = BiggerDTOWithoutCommission.class, source = Bigger.class)
    PackageDTOWithoutCommission packageToPackageDTOWithoutCommissionId(Package package1);

    @Mapping(target = "commission", ignore = true)
    @Mapping(target = "price", ignore = true)
    Package packageDTOWCToPackage(PackageDTOWithoutCommission packageDTOWithoutCommission);

    List<PackageDTOWithoutCommission> packageListToPackageDTOWCList(List<Package> packages);
}
