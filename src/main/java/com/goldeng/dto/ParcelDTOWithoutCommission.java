package com.goldeng.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelDTOWithoutCommission extends PackageDTOWithoutCommission {

    @NotNull(message = "Es necesario el peso del paquete")
    private double weight;
}
