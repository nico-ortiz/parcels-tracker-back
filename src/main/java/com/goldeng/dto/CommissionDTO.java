package com.goldeng.dto;

import java.time.LocalDate;
import java.util.List;

import com.goldeng.model.enums.Status;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionDTO {
    
    private Long commissionId;

    @NotBlank(message = "La descripcion de la comision es necesaria")
    private String description;

    @NotNull(message = "Es necesario establecer el estado de la comision")
    private Status status;

    @NotNull(message = "Es necesario establecer la fecha de realizacion de la comision")
    @Past(message = "La fecha ingresada no es valida")
    private LocalDate date; 

    @NotNull(message = "Es necesario que se ingrese el precio de")
    @Digits(integer = 2, fraction = 3, message = "Precio ingresado no es valido")
    private float price;

    @NotNull(message = "Es necesario establecer el cliente que realizo la comision")
    private Long customerId;

    @NotNull(message = "Es necesario establecer el destinatario de la comision")
    private Long receiverId;

    @Valid
    private List<PackageDTOWithoutCommission> packages;
}
