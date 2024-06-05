package com.goldeng.dto;

import java.time.LocalDate;
import java.util.List;

import com.goldeng.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionDTO {
    
    private Long commissionId;

    private String description;

    private Status status;

    private LocalDate date; 

    private float price;

    private Long customerId;

    private Long receiverId;

    private List<PackageDTO> packages;
}
