package com.goldeng.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnvelopeDTO extends PackageDTO {

    @JsonIgnore
    private Long packageId;
    
    private Long envelopeId;
}
