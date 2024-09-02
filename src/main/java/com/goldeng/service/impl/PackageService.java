package com.goldeng.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.PackageDTO;
import com.goldeng.mapper.PackageMapper;
import com.goldeng.model.Package;
import com.goldeng.repository.PackageRepository;
import com.goldeng.service.IPackageService;
import com.goldeng.validator.ObjectsValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PackageService implements IPackageService {

    @Autowired
    private PackageRepository packageRepository;

    private PackageMapper packageMapper;

    @Autowired
    private CommissionService commissionService;

    private ObjectsValidator<PackageDTO> packageValidator;

    @Override
    public PackageDTO createPackage(PackageDTO packageDTO) {
        packageValidator.validate(packageDTO);
        CommissionDTO commission = commissionService.getCommission(packageDTO.getCommissionId());

        if (commission.getCommissionId() == null) {
            return new PackageDTO();
        } 

        Package packageSaved = packageRepository.save(packageMapper.packageDTOToPackage(packageDTO));
        return packageMapper.packageToPackageDTO(packageSaved);        
    }

    @Override
    public PackageDTO deletePackage(Long packageId) {
        Optional<Package> pkg = packageRepository.findById(packageId);

        if (!pkg.isPresent()) {
            return new PackageDTO();
        }
       
        packageRepository.delete(pkg.get());
        PackageDTO packageDeleted = packageMapper.packageToPackageDTO(pkg.get() );
        return packageDeleted;
    }

    @Override
    public PackageDTO updatePackageDTO(Long packageId, PackageDTO packageDTO) {
            packageValidator.validate(packageDTO);
            PackageDTO packageToUpdate = this.getPackageById(packageId);

            if (packageToUpdate.getPackageId() == null) {
                return new PackageDTO();
            }

            packageToUpdate.setDescription(packageDTO.getDescription());
            packageToUpdate.setPackageType(packageDTO.getPackageType());
            packageToUpdate.setCommissionId(packageDTO.getCommissionId());
            return this.createPackage(packageToUpdate);
    }

    @Override
    public PackageDTO getPackageById(Long packageId) {
        Optional<Package> packageDB = packageRepository.findById(packageId);

        if (!packageDB.isPresent()) {
            return new PackageDTO();
        }

        return packageMapper.packageToPackageDTO(packageDB.get());
    }
    
}
