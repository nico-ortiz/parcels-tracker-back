package com.goldeng.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class BiggerDTO extends PackageDTO {

    @NotNull(message = "El ancho del paquete es necesario")
    private double width;

    @NotNull(message = "El alto del paquete es necesario")
    private double height;

    @NotNull(message = "El peso] del paquete es necesario")
    private double weight;
}
