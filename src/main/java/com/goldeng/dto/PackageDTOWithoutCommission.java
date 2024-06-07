package com.goldeng.dto;

import com.goldeng.model.enums.PackageType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTOWithoutCommission {
    
    private Long packageId;

    private String description;

    private PackageType packageType;
}
