package com.goldeng.service;

import java.util.List;

import com.goldeng.dto.CommissionDTOWithoutCustomer;
import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.CustomerDTOWithCommissions;

public interface ICustomerService {
    
    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomer(Long customerId);

    CustomerDTO deleteCustomer(Long customerId);

    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);

    CustomerDTOWithCommissions getCustomerWithCommissions(Long customerId);

    List<CommissionDTOWithoutCustomer> getCustomerCommissions(Long customerId);
}
