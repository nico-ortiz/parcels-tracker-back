package com.goldeng.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.CustomerDTOWithCommissions;
import com.goldeng.model.Customer;

@Mapper(
    componentModel = "spring",
    uses = {CommissionMapper.class})
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "commissions", ignore = true)
    @Mapping(target = "personId", source = "customerId")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    @Mapping(target = "customerId", source = "personId")
    CustomerDTO customerToCustomerDTO(Customer customer);

    List<CustomerDTO> customerListToCustomerDTOList(List<Customer> customers);

    @Mapping(target = "customerId", source = "personId")
    @Mapping(target = "commissions.packages.commissionId", ignore = true)
    CustomerDTOWithCommissions customerToCustomerDTOWC(Customer customer);

    @Mapping(target = "personId", source = "customerId")
    Customer customerDTOWCToCustomer(CustomerDTOWithCommissions customerDTOWithCommissions);
}
