package com.goldeng.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.EnvelopeDTO;
import com.goldeng.mapper.EnvelopeMapper;
import com.goldeng.model.packageSubClasses.Envelope;
import com.goldeng.repository.EnvelopeRepository;
import com.goldeng.service.ICommissionService;
import com.goldeng.service.IEnvelopeService;
import com.goldeng.validator.ObjectsValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnvelopeService implements IEnvelopeService {

    @Autowired
    private EnvelopeRepository envelopeRepository;

    private EnvelopeMapper envelopeMapper;

    @Autowired
    private ICommissionService commissionService;

    private ObjectsValidator<EnvelopeDTO> envelopeValidator;

    private static final double PRICE_ENVELOPE = 7000;

    @Override
    public EnvelopeDTO getEnvelopeById(Long envelopeId) {
        Optional<Envelope> envelopeDB = envelopeRepository.findById(envelopeId);

        if (!envelopeDB.isPresent()) {
            return new EnvelopeDTO();
        }

        return envelopeMapper.envelopeToEnvelopeDTO(envelopeDB.get());
    }

    @Override
    public EnvelopeDTO createEnvelope(EnvelopeDTO envelopeDTO) {
        envelopeValidator.validate(envelopeDTO);

        CommissionDTO commission = commissionService.getCommission(envelopeDTO.getCommissionId());

        if (commission.getCommissionId() == null) {
            return new EnvelopeDTO();
        }

        envelopeDTO.setPrice(PRICE_ENVELOPE);
        Envelope envelopeSaved = envelopeRepository.save(envelopeMapper.envelopeDTOToEnvelope(envelopeDTO));
        return envelopeMapper.envelopeToEnvelopeDTO(envelopeSaved);
    }

    @Override
    public EnvelopeDTO deleteEnvelope(Long envelopeId) {
        EnvelopeDTO envelopeDTO = this.getEnvelopeById(envelopeId);

        if (envelopeDTO.getPackageId() == null) {
            return new EnvelopeDTO();
        }

        envelopeRepository.delete(envelopeMapper.envelopeDTOToEnvelope(envelopeDTO));
        return envelopeDTO;
    }

    @Override
    public EnvelopeDTO updateEnvelope(Long envelopeId, String description) {
        Optional<Envelope> envelope = this.envelopeRepository.findById(envelopeId);

        if (!envelope.isPresent()) {
            return new EnvelopeDTO();
        }

        Envelope envelopeUpdated = envelope.get();
        envelopeUpdated.setDescription(description);
        return envelopeMapper.envelopeToEnvelopeDTO(this.envelopeRepository.save(envelopeUpdated));
    }   
    
}
