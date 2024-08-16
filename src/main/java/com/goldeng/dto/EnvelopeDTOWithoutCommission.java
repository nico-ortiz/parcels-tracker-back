package com.goldeng.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvelopeDTOWithoutCommission extends PackageDTOWithoutCommission {
    
    @JsonIgnore
    private Long packageId;

    private Long envelopeId;
}
