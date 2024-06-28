package com.goldeng.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.goldeng.dto.CommissionDTOWithoutCustomer;
import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.CustomerDTOWithCommissions;
import com.goldeng.service.ICustomerService;

// @CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(this.customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return new ResponseEntity<>(this.customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long customerId) {
        CustomerDTO customerDTO = this.customerService.getCustomer(customerId);
        if (customerDTO.getCustomerId() == null) {
            return new ResponseEntity<>(HttpStatus. NOT_FOUND);
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping("/del/{customerId}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Long customerId) {
        CustomerDTO customerDTO = this.customerService.deleteCustomer(customerId);
        if (customerDTO.getCustomerId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customerUpdated = this.customerService.updateCustomer(customerId, customerDTO);
        if (customerUpdated.getCustomerId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
    }

    @GetMapping("/withComm/{customerId}")
    public ResponseEntity<CustomerDTOWithCommissions> getCustomerWithCommissions(@PathVariable Long customerId) {
        CustomerDTOWithCommissions customerDTO = this.customerService.getCustomerWithCommissions(customerId);
        if (customerDTO.getCustomerId() == null) {
            return new ResponseEntity<>(HttpStatus. NOT_FOUND);
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping("/{customerId}/commissions")
    public ResponseEntity<List<CommissionDTOWithoutCustomer>> getCustomerCommissions(@PathVariable Long customerId) {
        List<CommissionDTOWithoutCustomer> commissions = this.customerService.getCustomerCommissions(customerId);
        if (commissions == null) {
            return new ResponseEntity<>(HttpStatus. NOT_FOUND);
        }
        return new ResponseEntity<>(commissions, HttpStatus.OK);
    }
}
