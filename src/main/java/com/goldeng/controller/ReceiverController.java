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

import com.goldeng.dto.ReceiverDTO;
import com.goldeng.dto.ReceiverDTOWithCommissions;
import com.goldeng.service.impl.ReceiverService;

@CrossOrigin
@RestController
@RequestMapping("/receivers")
public class ReceiverController {
    
    @Autowired
    private ReceiverService receiverService;

    @PostMapping("/create")
    public ResponseEntity<ReceiverDTO> createReceiver(@RequestBody ReceiverDTO receiverDTO) {
        return new ResponseEntity<>(receiverService.createReceiver(receiverDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{receiverId}")
    public ResponseEntity<ReceiverDTO> getReceiver(@PathVariable Long receiverId) {
        ReceiverDTO receiverDTO = receiverService.getReceiver(receiverId);

        if (receiverDTO.getReceiverId() == null) {
            return new ResponseEntity<>(HttpStatus. NOT_FOUND);
        }
        return new ResponseEntity<>(receiverDTO, HttpStatus.OK);
    }

    @DeleteMapping("/del/{receiverId}")
    public ResponseEntity<ReceiverDTO> deleteReceiver(@PathVariable Long receiverId) {
        ReceiverDTO receiverDTO = receiverService.deleteReceiver(receiverId);

        if (receiverDTO.getReceiverId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receiverDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{receiverId}")
    public ResponseEntity<ReceiverDTO> updateReceiver(@PathVariable Long receiverId, @RequestBody ReceiverDTO receiverDTO) {
        ReceiverDTO receiverUpdated = receiverService.updateReceiver(receiverId, receiverDTO);

        if (receiverUpdated.getReceiverId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(receiverUpdated, HttpStatus.OK);
    }

    @GetMapping("withComm/{receiverId}")
    public ResponseEntity<ReceiverDTOWithCommissions> getReceiverWithReceivedCommissions(@PathVariable Long receiverId) {
        ReceiverDTOWithCommissions receiverDTO = receiverService.getReceiverWithReceivedCommissions(receiverId);

        if (receiverDTO.getReceiverId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receiverDTO, HttpStatus.OK);
    }
}
