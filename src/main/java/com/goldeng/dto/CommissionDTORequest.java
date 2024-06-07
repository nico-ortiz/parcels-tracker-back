package com.goldeng.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionDTORequest {
    
    @NotBlank(message = "La descripcion de la comision es necesaria")
    private String description;

    @NotNull(message = "Es necesario establecer el cliente que realizo la comision")
    private Long customerId;

    @NotNull(message = "Es necesario establecer el destinatario de la comision")
    private Long receiverId;
}
