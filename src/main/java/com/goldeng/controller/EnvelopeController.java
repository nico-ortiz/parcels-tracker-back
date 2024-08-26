package com.goldeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldeng.dto.EnvelopeDTO;
import com.goldeng.service.IEnvelopeService;

@CrossOrigin
@RestController
@RequestMapping("/envelopes")
public class EnvelopeController {
    
    @Autowired
    private IEnvelopeService envelopeService;

    @PostMapping("/create")
    public ResponseEntity<EnvelopeDTO> createEnvelope(@RequestBody EnvelopeDTO envelopeRequest) {
        EnvelopeDTO envelopeSaved =  envelopeService.createEnvelope(envelopeRequest);

        if (envelopeSaved.getEnvelopeId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(envelopeSaved, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{envelopeId}")
    public ResponseEntity<EnvelopeDTO> getEnvelope(@PathVariable Long envelopeId) { 
        EnvelopeDTO envelopeDTO = envelopeService.getEnvelopeById(envelopeId);

        if (envelopeDTO.getEnvelopeId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(envelopeDTO, HttpStatus.OK);
    }
}
