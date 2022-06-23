package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.mapper.CustomerMapper;
import com.federicobonel.restapiapplication.api.v1.model.CustomerDTO;
import com.federicobonel.restapiapplication.controllers.v1.CustomerController;
import com.federicobonel.restapiapplication.model.Customer;
import com.federicobonel.restapiapplication.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    public static final long ID = 1L;
    public static final String NAME = "Federico";
    public static final String LASTNAME = "Bonel";
    public static final String CUSTOMER_URL = CustomerController.BASE_URL_CUSTOMERS + "/" + ID;
    final CustomerMapper customerMapper = CustomerMapper.INSTANCE;
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

        assertEquals(CUSTOMER_URL, foundCustomer.getCustomerUrl());
        verify(customerRepository).findById(ID);
    }

    @Test
    void createCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO savedCustomer = customerService.createCustomer(customerMapper.customerToCustomerDTO(customer));

        assertEquals(NAME, savedCustomer.getName());
        assertEquals(LASTNAME, savedCustomer.getLastname());
        assertEquals(CUSTOMER_URL, savedCustomer.getCustomerUrl());
    }

    @Test
    void updateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO savedCustomer = customerService.updateCustomer(ID, customerMapper.customerToCustomerDTO(customer));

        assertEquals(NAME, savedCustomer.getName());
        assertEquals(LASTNAME, savedCustomer.getLastname());
        assertEquals(CUSTOMER_URL, savedCustomer.getCustomerUrl());
    }

    @Test
    void deleteCustomerById() {
        customerService.deleteCustomerById(ID);

        verify(customerRepository).deleteById(ID);
    }
}