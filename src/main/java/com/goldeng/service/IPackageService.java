package com.goldeng.service;

import com.goldeng.dto.PackageDTO;

public interface IPackageService {
    
    PackageDTO createPackage(PackageDTO packageDTO);

    PackageDTO deletePackage(Long packageId);

    PackageDTO updatePackageDTO(Long packageId, PackageDTO packageDTO);

    PackageDTO getPackageById(Long packageId);
}
