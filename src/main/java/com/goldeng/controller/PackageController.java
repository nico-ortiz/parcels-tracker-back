package com.goldeng.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.goldeng.dto.PackageDTO;
import com.goldeng.service.impl.PackageService;

@CrossOrigin
@RestController
@RequestMapping("/packages")
public class PackageController {
    
    @Autowired
    private PackageService packageService;

    @PostMapping("/create")
    public ResponseEntity<PackageDTO> createPackage(@RequestBody PackageDTO packageDTO) {
        PackageDTO packageSaved = packageService.createPackage(packageDTO);

        if (packageSaved.getPackageId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(packageSaved, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/del/{packageId}")
    public ResponseEntity<PackageDTO> deletePackage(@PathVariable Long packageId) {
        PackageDTO packageDeleted = packageService.deletePackage(packageId);
        
        if (packageDeleted.getPackageId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(packageDeleted, HttpStatus.OK);
    }

    @PutMapping("/update/{packageId}")
    public ResponseEntity<PackageDTO> updatePackage(@PathVariable Long packageId, @RequestBody PackageDTO packageDTO) {
        PackageDTO packageUpdated = packageService.updatePackageDTO(packageId, packageDTO);

        if (packageUpdated.getPackageId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(packageUpdated, HttpStatus.OK);
    }

    @GetMapping("/{packageId}")
    public ResponseEntity<PackageDTO> getPackage(@PathVariable Long packageId) {
        PackageDTO packageDTO = packageService.getPackageById(packageId);

        if (packageDTO.getPackageId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(packageDTO, HttpStatus.OK);
    }
}
