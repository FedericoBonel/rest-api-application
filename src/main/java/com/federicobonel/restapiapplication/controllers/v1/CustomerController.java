package com.federicobonel.restapiapplication.controllers.v1;

import com.federicobonel.restapiapplication.api.v1.model.CustomerDTO;
import com.federicobonel.restapiapplication.api.v1.model.CustomerListDTO;
import com.federicobonel.restapiapplication.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAll() {
        log.info("Getting all customers");

        CustomerListDTO customerListDTO = new CustomerListDTO(customerService.getAll());

        return new ResponseEntity<>(customerListDTO, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long customerId) {
        log.info("Getting customer with id " + customerId);

        return new ResponseEntity<>(customerService.getById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        log.info("Creating customer " + customerDTO.getName());

        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        log.info("Updating customer with id " + customerId);

        return new ResponseEntity<>(customerService.updateCustomer(customerId, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        log.info("Patching customer with id " + customerId);

        return new ResponseEntity<>(customerService.patchCustomer(customerId, customerDTO), HttpStatus.OK);
    }

}
