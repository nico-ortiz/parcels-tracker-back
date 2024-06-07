package com.goldeng.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverDTO {
    private Long receiverId;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private LocalDate date;

    private LocalTime openingHour;

    private LocalTime closingHour;
}
