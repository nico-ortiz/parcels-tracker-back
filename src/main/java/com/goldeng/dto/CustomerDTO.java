package com.goldeng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String firstName;
    
    private String lastName;

    private String address;

    private String phoneNumber;
    
    private Long customerId;
    
    private String dni;

    private String email;

    private String cuit;

    private String password;
}
