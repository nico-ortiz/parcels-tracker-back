package com.goldeng.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverDTOWithCommissions {
    private Long receiverId;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private LocalDate date;

    private LocalTime openingHour;

    private LocalTime closingHour;
    
    private List<CommissionDTOWithoutReceiver> receivedCommissions;    
}
