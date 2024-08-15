package com.goldeng.dto;

import com.goldeng.model.enums.PackageType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvelopeDTO {
    
    private Long envelopeId;

    @NotBlank(message = "La descripcion del sobre es necesaria")
    private String description;

    @NotNull(message = "Es necesario el precio del sobre")
    private double price;

    @NotNull(message = "El tipo de paquete es necesario")
    private PackageType packageType;

    @NotNull(message = "Es necesario indicar la comision a la que pertenece")
    private Long commissionId;
}
