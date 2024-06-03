package com.goldeng.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.CustomerDTOWithCommissions;
import com.goldeng.mapper.CustomerMapper;
import com.goldeng.model.Commission;
import com.goldeng.model.Customer;
import com.goldeng.repository.CustomerRepository;
import com.goldeng.service.ICustomerService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerMapper customerMapper;

    // private PasswordEncoder passwordEncoder;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        CustomerDTO customerDTOSaved = customerMapper.customerToCustomerDTO(this.customerRepository.save(customer));
        return customerDTOSaved;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerMapper.customerListToCustomerDTOList(customerRepository.findAll());
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            return customerMapper.customerToCustomerDTO(customerOptional.get());
        }

        return new CustomerDTO();
    }

    @Override
    public CustomerDTO deleteCustomer(Long customerId) {
        CustomerDTO customerDTO = this.getCustomer(customerId);

        if (customerDTO.getCustomerId() != null) {
            customerRepository.delete(customerMapper.customerDTOToCustomer(customerDTO));
            return customerDTO;
        }
        return new CustomerDTO();
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        CustomerDTO customerDB = this.getCustomer(customerId);

        if (customerDB.getCustomerId() == null) {
            return new CustomerDTO();
        }

        customerDB.setFirstName(customerDTO.getFirstName());
        customerDB.setLastName(customerDTO.getLastName());
        customerDB.setAddress(customerDTO.getAddress());
        customerDB.setPhoneNumber(customerDTO.getPhoneNumber());
        customerDB.setDni(customerDTO.getDni());
        customerDB.setEmail(customerDB.getEmail());
        customerDB.setCuit(customerDTO.getCuit());
        //encode password
        customerDB.setPassword(customerDTO.getPassword());
        return this.createCustomer(customerDTO);
    }

    @Override
    public CustomerDTOWithCommissions getCustomerWithCommissions(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            return customerMapper.customerToCustomerDTOWC(customerOptional.get());
        }

        return new CustomerDTOWithCommissions();
    }

    @Override
    public List<Commission> getCustomerCommissions(Long customerId) {
        CustomerDTOWithCommissions customerDTO = this.getCustomerWithCommissions(customerId);

        if (customerDTO.getCustomerId() != null) {
            return customerDTO.getCommissions();
        }
        return null;      
    }
    
}
