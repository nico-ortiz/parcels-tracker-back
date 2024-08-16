package com.goldeng.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelDTOWithoutCommission extends PackageDTOWithoutCommission {
    
    @JsonIgnore
    private Long packageId;

    private Long parcelId;

    @NotNull(message = "Es necesario el peso del paquete")
    private double weight;
}
