package com.goldeng.service;

import com.goldeng.dto.EnvelopeDTO;

public interface IEnvelopeService {
    
    EnvelopeDTO getEnvelopeById(Long envelopeId);

    EnvelopeDTO createEnvelope(EnvelopeDTO envelopeDTO);
}
