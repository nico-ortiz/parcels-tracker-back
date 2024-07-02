package com.goldeng.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long customerId;

    @NotBlank(message = "El nombre del cliente no es valido")
    private String firstName;
    
    @NotBlank(message = "El apellido del cliente no es valido")
    private String lastName;

    @NotBlank(message = "Es necesario ingresar un domicilio valido")
    private String address;

    @NotBlank(message = "El numero de telefono ingresado no es valido")
    @Size(min = 9, max = 20, message = "El numero de telefono ingresado no es correcto")
    private String phoneNumber;
    
    @NotBlank(message = "Es necesario que ingrese un numero de DNI")
    @Size(min = 8, max = 8, message = "El numero de DNI ingresado no es valido")
    private String dni;

    @NotBlank(message = "Es necesario que ingrese un email")
    @Email(message = "El email ingresado no es valido")
    private String email;

    @NotBlank(message = "Es necesario un numero de CUIT")
    @Size(min = 11, max = 11, message = "El numero de CUIT ingresado no es valido")
    private String cuit;

    @NotBlank(message = "Es necesario que ingrese una contraseña")
    @Size(min = 8, max = 20, message = "La contraseña debe ser mínimo de 8 caracteres")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[.!@#$&*%_\\-=])[a-zA-Z0-9.!@#$&*%_\\-=]+$",
        message = "La contraseña debe ser mínimo de 8 caracteres al menos una letra mayúscula, y opcionalmente solo se permite los siguientes caracteres especiales: .!@#$&*%_-=;"
    )
    private String password;
}
