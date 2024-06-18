package com.goldeng.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;

import com.goldeng.dataProvider.CustomerData;
import com.goldeng.dto.CommissionDTOWithoutCustomer;
import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.CustomerDTOWithCommissions;
import com.goldeng.mapper.CustomerMapper;
import com.goldeng.model.Customer;
import com.goldeng.repository.CustomerRepository;
import com.goldeng.service.impl.CustomerService;
import com.goldeng.validator.ObjectsValidator;

@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackageClasses = {CustomerMapper.class})
public class CustomerServiceTest {
    
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private ObjectsValidator<CustomerDTO> customerValidator;
    
    @InjectMocks
    private CustomerService customerService;

    @Test
    void createCustomerTest() {
        //Given
        CustomerDTO customerDTOToSave = CustomerData.newCustomerDTOMock();

        //When
        doNothing().when(this.customerValidator).validate(customerDTOToSave);
        when(this.customerMapper.customerDTOToCustomer(any(CustomerDTO.class))).thenReturn(CustomerData.newCustomerMock());
        when(this.customerMapper.customerToCustomerDTO(any(Customer.class))).thenReturn(customerDTOToSave);
        when(this.customerRepository.save(any(Customer.class))).thenReturn(CustomerData.newCustomerMock());

        @SuppressWarnings("unused")
        CustomerDTO customerDTOSaved = this.customerService.createCustomer(customerDTOToSave);
        
        //Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(this.customerRepository).save(customerArgumentCaptor.capture());
        assertEquals(22L, customerArgumentCaptor.getValue().getPersonId());
        assertEquals( "rnazario@gmail.com", customerArgumentCaptor.getValue().getEmail());
    }

    @Test
    void getCustomerByIdTest() {
        //Given
        Long customerId = 1L;

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerData.customerMock()));
        when(this.customerMapper.customerToCustomerDTO(any(Customer.class))).thenReturn(CustomerData.customerDTOMock());

        CustomerDTO customerDTO = this.customerService.getCustomer(customerId);

        //Then
        assertNotNull(customerDTO);
        assertEquals("Sinatra", customerDTO.getLastName());
        verify(this.customerRepository).findById(anyLong());
    }

    @Test
    void whenIdNotExistsTest() {
        //Given
        Long customerId = 2L;

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        CustomerDTO customerDTO = this.customerService.getCustomer(customerId);

        //Then
        assertNull(customerDTO.getCustomerId());
        assertNull(customerDTO.getFirstName());
        verify(this.customerRepository).findById(anyLong());
    }

    @Test
    void getCustomersTest() {
        //When
        when(this.customerMapper.customerListToCustomerDTOList(anyList())).thenReturn(CustomerData.customerDTOListMock());
        when(this.customerRepository.findAll()).thenReturn(CustomerData.customerListMock());
        List<CustomerDTO> customers = this.customerService.getCustomers();

        //Then
        assertNotNull(customers);
        assertFalse(customers.isEmpty());
        assertEquals(customers.get(0).getFirstName(), "Lionel");
        verify(this.customerRepository).findAll();
    }

    @Test
    void deleteCustomerTest() {
        //Given
        Long customerId = 1L;

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerData.customerMock()));
        when(this.customerMapper.customerToCustomerDTO(any(Customer.class))).thenReturn(CustomerData.customerDTOMock());

        doNothing().when(this.customerRepository).delete(CustomerData.customerMock());
        when(this.customerMapper.customerDTOToCustomer(any(CustomerDTO.class))).thenReturn(CustomerData.customerMock());
        
        CustomerDTO customerDeleted = this.customerService.deleteCustomer(customerId);

        //Then
        assertNotNull(customerDeleted.getCustomerId());
        assertEquals("Sinatra", customerDeleted.getLastName());
        verify(this.customerRepository).delete(any(Customer.class));
    }

    @Test
    void whenIdNotExistsNotDeleteTest() {
        //Given
        Long customerId = 2L;

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        CustomerDTO customerDeleted = this.customerService.deleteCustomer(customerId);

        //Then
        assertNull(customerDeleted.getCustomerId());
        assertNull(customerDeleted.getEmail());
    }

    @Test
    void updateCustomerTest() {
        //Given
        Long customerId = 1L;
        CustomerDTO newCustomerData = new CustomerDTO(1L, "Ramon", "Sinatra", "Buenos Aires 21", "3584333123", "12123123", "rsinatra@gmail.com", "12121231231", "Sin4tr4.");    

        //When
        doNothing().when(this.customerValidator).validate(newCustomerData);
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerData.customerMock()));
        when(this.customerMapper.customerToCustomerDTO(any(Customer.class))).thenReturn(CustomerData.customerDTOMock());
        when(this.customerMapper.customerDTOToCustomer(any(CustomerDTO.class))).thenReturn(CustomerData.customerMock());
        when(this.customerRepository.save(any(Customer.class))).thenReturn(CustomerData.customerUpdatedMock());

        CustomerDTO customerUpdated = this.customerService.updateCustomer(customerId, newCustomerData);

        //Then
        assertNotNull(customerUpdated);
        assertNotEquals("rsinatra@gmail.com", customerUpdated.getEmail());
        assertEquals("3584333123", customerUpdated.getPhoneNumber());
        verify(this.customerRepository).save(any(Customer.class));
    }

    @Test
    void whenIdNotExistsNotUpdatedCustomerTest() {
        //Given
        Long customerId = 2L;
        CustomerDTO newCustomerData = new CustomerDTO(1L, "Ramon", "Sinatra", "Buenos Aires 21", "3584333123", "12123123", "rsinatra@gmail.com", "12121231231", "Sin4tr4.");    

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        CustomerDTO customerDeleted = this.customerService.updateCustomer(customerId, newCustomerData);

        //Then
        assertNull(customerDeleted.getCustomerId());
        assertNull(customerDeleted.getEmail());
    }

    @Test
    void getCustomerWithCommissionsTest() {
        //Given
        Long customerId = 1L;

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerData.customerMock()));
        when(this.customerMapper.customerToCustomerDTOWC(any(Customer.class))).thenReturn(CustomerData.customerDTOWithCommissionsMock());
        
        CustomerDTOWithCommissions customer = this.customerService.getCustomerWithCommissions(customerId);
        
        //Then
        assertNotNull(customer);
        assertTrue(!customer.getCommissions().isEmpty());
        assertTrue(customer.getCommissions().get(0).getPackages().size() == 2);
        verify(this.customerRepository).findById(customerId);
    }

    @Test
    void whenNotExistsCustomerIdReturnEmptyCustomerTest() {
        //Given
        Long customerId = 22L;

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        CustomerDTOWithCommissions customerDTOWithCommissions = this.customerService.getCustomerWithCommissions(customerId);

        //Then
        assertNull(customerDTOWithCommissions.getCustomerId());
        assertNull(customerDTOWithCommissions.getDni());
    }

    @Test
    void getCustomerCommissionsTest() {
        //Given
        Long customerId = 1L;

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerData.customerMock()));
        when(this.customerMapper.customerToCustomerDTOWC(any(Customer.class))).thenReturn(CustomerData.customerDTOWithCommissionsMock());

        List<CommissionDTOWithoutCustomer> commissions = this.customerService.getCustomerCommissions(customerId);

        //Then
        assertTrue(!commissions.isEmpty());
        assertTrue(commissions.size() == 1);
        verify(this.customerRepository).findById(customerId);
    }

    @Test
    void whenIdCustomerNotExistsReturnAnEmptyListTest() {
        //Given
        Long customerId = 22L;

        //When
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        List<CommissionDTOWithoutCustomer> commissions = this.customerService.getCustomerCommissions(customerId);

        //Then
        assertNull(commissions);
    }
}