package com.goldeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldeng.dto.BiggerDTO;
import com.goldeng.service.IBiggerService;

@CrossOrigin
@RestController
@RequestMapping("/biggers")
public class BiggerController {
    
    @Autowired
    private IBiggerService biggerService;

    @PostMapping("/create")
    public ResponseEntity<BiggerDTO> createBigger(@RequestBody BiggerDTO biggerRequest) {
        BiggerDTO biggerSaved = biggerService.createBigger(biggerRequest);

        if (biggerSaved.getBiggerId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(biggerSaved, HttpStatus.CREATED);
    }

    @GetMapping("/{biggerId}")
    public ResponseEntity<BiggerDTO> getBigerById(@PathVariable Long biggerId) {
        BiggerDTO isBiggerExists = biggerService.getBigger(biggerId);

        if (isBiggerExists.getBiggerId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(isBiggerExists, HttpStatus.OK);
    }

    @DeleteMapping("/del/{biggerId}")
    public ResponseEntity<BiggerDTO> deleteBiggerById(@PathVariable Long biggerId) {
        BiggerDTO biggerDeleted = this.biggerService.deleteBigger(biggerId);

        if (biggerDeleted.getBiggerId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(biggerDeleted, HttpStatus.ACCEPTED);
    }
}
