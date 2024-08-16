package com.goldeng.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class BiggerDTO extends PackageDTO {
    
    @JsonIgnore
    private Long packageId;

    private Long biggerId;

    @NotNull(message = "El ancho del paquete es necesario")
    private double width;

    @NotNull(message = "El alto del paquete es necesario")
    private double height;

    @NotNull(message = "El peso] del paquete es necesario")
    private double weight;
}
