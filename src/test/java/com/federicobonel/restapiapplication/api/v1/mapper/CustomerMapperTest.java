package com.federicobonel.restapiapplication.api.v1.mapper;

import com.federicobonel.restapiapplication.api.v1.model.CustomerDTO;
import com.federicobonel.restapiapplication.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final long ID = 1L;
    public static final String NAME = "Federico";
    public static final String LASTNAME = "Bonel";
    public static final String CUSTOMER_URL = "https://randomUrl.com";

    Customer customer;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(ID);
        customer.setName(NAME);
        customer.setLastname(LASTNAME);
        customer.setCustomerUrl(CUSTOMER_URL);
    }

    @Test
    void customerToCustomerDTO() {

        CustomerDTO mappedCustomer = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customer.getId(), mappedCustomer.getId());
        assertEquals(customer.getName(), mappedCustomer.getName());
        assertEquals(customer.getLastname(), mappedCustomer.getLastname());
        assertEquals(customer.getCustomerUrl(), mappedCustomer.getCustomerUrl());

    }

}