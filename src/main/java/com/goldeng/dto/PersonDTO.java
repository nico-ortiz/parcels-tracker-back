package com.goldeng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    
    private Long personId;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;
}
