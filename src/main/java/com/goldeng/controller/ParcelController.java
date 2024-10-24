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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goldeng.dto.ParcelDTO;
import com.goldeng.model.enums.PackageType;
import com.goldeng.service.IParcelService;

@CrossOrigin
@RestController
@RequestMapping("/parcels")
public class ParcelController {
    
    @Autowired
    private IParcelService parcelService;

    @PostMapping("/create")
    public ResponseEntity<ParcelDTO> createParcel(@RequestBody ParcelDTO parcelRequest) {
        ParcelDTO parcelSaved = parcelService.createParcel(parcelRequest);

        if (parcelSaved.getPackageId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(parcelSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{parcelId}")
    public ResponseEntity<ParcelDTO> getParcelById(@PathVariable Long parcelId) {
        ParcelDTO existsParcel = parcelService.getParcelById(parcelId);

        if (existsParcel.getPackageId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(existsParcel, HttpStatus.OK);
    } 

    @DeleteMapping("/del/{parcelId}")
    public ResponseEntity<ParcelDTO> deleteParcelById(@PathVariable Long parcelId)  {
        ParcelDTO parcelDTO = this.parcelService.deleteParcelById(parcelId);

        if (parcelDTO.getPackageId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(parcelDTO, HttpStatus.ACCEPTED); 
    }

    @PutMapping("/update/{parcelId}")
    public ResponseEntity<ParcelDTO> updateParcelById(@PathVariable Long parcelId,
                                                    @RequestParam String description,
                                                    @RequestParam PackageType packageType) {
        ParcelDTO parcelDTO = this.parcelService.updateParcelById(parcelId, description, packageType);
        
        if (parcelDTO.getPackageId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 

        return new ResponseEntity<>(parcelDTO, HttpStatus.ACCEPTED);
    }
}
