package com.federicobonel.restapiapplication.controllers.v1;

import api.v1.model.CustomerDTO;
import api.v1.model.CustomerListDTO;
import com.federicobonel.restapiapplication.config.SwaggerConfig;
import com.federicobonel.restapiapplication.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {SwaggerConfig.CUSTOMER_TAG})
@RestController
@RequestMapping(CustomerController.BASE_URL_CUSTOMERS)
public class CustomerController {

    public static final String BASE_URL_CUSTOMERS = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Gets all customers")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAll() {
        log.info("Getting all customers");

        CustomerListDTO list = new CustomerListDTO();
        list.getCustomers().addAll(customerService.getAll());
        return list;
    }

    @ApiOperation(value = "Gets the customer with the given ID", notes = "If no customer is found 404 code is returned")
    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO findById(@PathVariable Long customerId) {
        log.info("Getting customer with id " + customerId);

        return customerService.getById(customerId);
    }

    @ApiOperation(value = "Creates new customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        log.info("Creating customer " + customerDTO.getName());

        return customerService.createCustomer(customerDTO);
    }

    @ApiOperation(value = "Replaces all the information of the given customer",
            notes = "If any field is not specified in the request payload, it will be set to a 'null' value")
    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        log.info("Updating customer with id " + customerId);

        return customerService.updateCustomer(customerId, customerDTO);
    }

    @ApiOperation(value = "Updates the customer",
            notes = "Not specified fields are not going to change")
    @PatchMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        log.info("Patching customer with id " + customerId);

        return customerService.patchCustomer(customerId, customerDTO);
    }

    @ApiOperation(value = "Deletes the customer with the given ID",
            notes = "If the customer is not present it will return a 404 code")
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long customerId) {
        log.info("Deleting customer with id " + customerId);

        customerService.deleteCustomerById(customerId);
    }

}
