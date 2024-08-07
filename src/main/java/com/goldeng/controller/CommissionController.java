package com.goldeng.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.CommissionDTORequest;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.service.ICommissionService;

@CrossOrigin
@RestController
@RequestMapping("/commissions")
public class CommissionController {
    
    @Autowired
    private ICommissionService commissionService;

    @PostMapping("/create")
    public ResponseEntity<CommissionDTO> createCommission(@RequestBody CommissionDTORequest commissionDTO) {
        CommissionDTO commissionCreated = commissionService.createCommission(commissionDTO);

        /*No se encontro Customer ni Receiver con los Ids indicados */
        if (commissionCreated.getCommissionId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(commissionCreated, HttpStatus.CREATED);
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

    @GetMapping("/by-date")
    public ResponseEntity<List<CommissionDTO>> getCommissionsByDate(@RequestParam LocalDate date) {
        List<CommissionDTO> commissions = commissionService.getCommissionsByDate(date);
        return new ResponseEntity<>(commissions, HttpStatus.OK);
    }

    @GetMapping("/{commissionId}/packages")
    public ResponseEntity<List<PackageDTOWithoutCommission>> getPackagesOfCommission(@PathVariable Long commissionId) {
        List<PackageDTOWithoutCommission> packages = commissionService.getPackagesByCommission(commissionId);

        if (packages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }
}
