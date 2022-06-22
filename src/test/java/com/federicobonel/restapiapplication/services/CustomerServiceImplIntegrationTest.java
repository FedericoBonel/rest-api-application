package com.federicobonel.restapiapplication.services;


import com.federicobonel.restapiapplication.api.v1.mapper.CustomerMapper;
import com.federicobonel.restapiapplication.api.v1.model.CustomerDTO;
import com.federicobonel.restapiapplication.datainitializer.DataInitializer;
import com.federicobonel.restapiapplication.repositories.CategoryRepository;
import com.federicobonel.restapiapplication.repositories.CustomerRepository;
import com.federicobonel.restapiapplication.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIntegrationTest {

    final static String NAME = "New Name";
    final static String LASTNAME = "New Lastname";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    CustomerDTO customerToPatch;

    @BeforeEach
    void setUp() {

        // Initializing data
        new DataInitializer(categoryRepository, customerRepository, vendorRepository).run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);

        customerToPatch = new CustomerDTO();
    }

    @Test
    void patchName() {
        Long idFirstCustomer = findFirstId();
        customerToPatch.setId(idFirstCustomer);
        customerToPatch.setName(NAME);

        CustomerDTO savedCustomer = customerService.patchCustomer(idFirstCustomer, customerToPatch);

        assertNotNull(savedCustomer);
        assertEquals(idFirstCustomer, savedCustomer.getId());
        assertEquals(NAME, savedCustomer.getName());
        assertNotEquals(LASTNAME, savedCustomer.getLastname());
    }

    @Test
    void patchLastName() {
        Long idFirstCustomer = findFirstId();
        customerToPatch.setId(idFirstCustomer);
        customerToPatch.setLastname(LASTNAME);

        CustomerDTO savedCustomer = customerService.patchCustomer(idFirstCustomer, customerToPatch);

        assertNotNull(savedCustomer);
        assertEquals(idFirstCustomer, savedCustomer.getId());
        assertEquals(LASTNAME, savedCustomer.getLastname());
        assertNotEquals(NAME, savedCustomer.getLastname());
    }

    private Long findFirstId() {
        return customerRepository.findAll().get(0).getId();
    }
}
