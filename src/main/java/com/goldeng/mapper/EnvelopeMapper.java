package com.goldeng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.EnvelopeDTO;
import com.goldeng.dto.EnvelopeDTOWithoutCommission;
import com.goldeng.model.packageSubClasses.Envelope;

@Mapper(
    componentModel = "spring"
)
public interface EnvelopeMapper {
    EnvelopeMapper INSTANCE = Mappers.getMapper(EnvelopeMapper.class);

    @Mapping(target = "price", ignore = true)
    @Mapping(target = "commission.commissionId", source = "commissionId")
    Envelope envelopeDTOToEnvelope(EnvelopeDTO envelopeDTO);

    @Mapping(target = "commissionId", source = "commission.commissionId")
    EnvelopeDTO envelopeToEnvelopeDTO(Envelope envelope);
 
    EnvelopeDTOWithoutCommission envelopeToEnvelopeDTOWC(Envelope envelope);
}
