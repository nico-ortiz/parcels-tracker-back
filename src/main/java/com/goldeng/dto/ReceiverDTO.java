package com.goldeng.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverDTO {
    private Long receiverId;

    @NotBlank(message = "El nombre del cliente no es valido")
    private String firstName;

    @NotBlank(message = "El apellido del cliente no es valido")
    private String lastName;

    @NotBlank(message = "Es necesario ingresar un domicilio valido")
    private String address;

    @NotBlank(message = "El numero de telefono ingresado no es valido")
    @Size(min = 9, max = 20, message = "El numero de telefono ingresado no es correcto")
    private String phoneNumber;

    @NotNull(message = "Es necesaria la fecha de entrega")
    @FutureOrPresent(message = "La fecha no es valida")
    private LocalDate date;

    @NotNull(message = "Es necesario el horario a partir del cual puede recibir la comission")
    private LocalTime openingHour;

    @NotNull(message = "Es necesario el horario a hasta el cual puede recibir la comission")
    private LocalTime closingHour;
}
