package com.goldeng.service;

import com.goldeng.dto.EnvelopeDTO;
import com.goldeng.dto.EnvelopeDTORequest;

public interface IEnvelopeService {
    
    EnvelopeDTO getEnvelopeById(Long envelopeId);

    EnvelopeDTO createEnvelope(EnvelopeDTORequest envelopeDTO);
}
