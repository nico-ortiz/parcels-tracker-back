package com.goldeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.service.ICommissionService;

@RestController
@RequestMapping("/commissions")
public class CommissionController {
    
    @Autowired
    private ICommissionService commissionService;

    @PostMapping("/create")
    public ResponseEntity<CommissionDTO> createCommission(@RequestBody CommissionDTO commissionDTO) {
        return new ResponseEntity<>(commissionService.createCommission(commissionDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{commissionId}")
    public ResponseEntity<CommissionDTO> getCommission(@PathVariable Long commissionId) {
        CommissionDTO commissionDTO = commissionService.getCommission(commissionId);

        if (commissionDTO.getCommissionId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(commissionDTO, HttpStatus.OK);
    }

    @DeleteMapping("/del/{commissionId}")
    public ResponseEntity<CommissionDTO> deleteCommission(@PathVariable Long commissionId) {
        CommissionDTO commissionDTO = commissionService.deleteCommission(commissionId);

        if (commissionDTO.getCommissionId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(commissionDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{commissionId}")
    public ResponseEntity<CommissionDTO> updateCommission(@PathVariable Long commissionId, @RequestBody CommissionDTO commissionDTO) {
        CommissionDTO commissionUpdated = commissionService.updateCommission(commissionId, commissionDTO);

        if (commissionUpdated.getCommissionId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 

        return new ResponseEntity<>(commissionUpdated, HttpStatus.OK);
    }
}
