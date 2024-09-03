package com.goldeng.service;

import com.goldeng.dto.EnvelopeDTO;

public interface IEnvelopeService {
    
    EnvelopeDTO getEnvelopeById(Long envelopeId);

    EnvelopeDTO createEnvelope(EnvelopeDTO envelopeDTO);

    EnvelopeDTO deleteEnvelope(Long envelopeId);

    EnvelopeDTO updateEnvelope(Long envelopeId, String description);
}
