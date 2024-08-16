package com.goldeng.dto;

import org.springframework.validation.annotation.Validated;

import com.goldeng.model.enums.PackageType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PackageDTOWithoutCommission {
    
    private Long packageId;

    @NotBlank(message = "Es necesario ingresar una descripcion del paquete")
    private String description;

    @NotBlank(message = "Es necesario el precio del paquete")
    private float price;

    @NotBlank(message = "Es necesario establecer el tipo de paquete")
    private PackageType packageType;
}
