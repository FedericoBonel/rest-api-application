package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.mapper.CustomerMapper;
import com.federicobonel.restapiapplication.api.v1.model.CustomerDTO;
import com.federicobonel.restapiapplication.model.Customer;
import com.federicobonel.restapiapplication.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    public static final long ID = 1L;
    public static final String NAME = "Federico";
    public static final String LASTNAME = "Bonel";
    public static final String CUSTOMER_URL = "/api/v1/customers/" + ID;

    Customer customer;
    List<Customer> customers;

    @Mock
    CustomerRepository customerRepository;
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);

        customer = new Customer();
        customer.setId(ID);
        customer.setName(NAME);
        customer.setLastname(LASTNAME);

        customers = List.of(new Customer(), new Customer(), new Customer());
    }

    @Test
    void getAll() {
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> foundCustomers = customerService.getAll();

        assertEquals(customers.size(), foundCustomers.size());
        verify(customerRepository).findAll();
    }

    @Test
    void getById() {
        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));

        CustomerDTO foundCustomer = customerService.getById(ID);

        assertEquals(ID, foundCustomer.getId());
        assertEquals(CUSTOMER_URL, foundCustomer.getCustomerUrl());
        verify(customerRepository).findById(ID);
    }
}