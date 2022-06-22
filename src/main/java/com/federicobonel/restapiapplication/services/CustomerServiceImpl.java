package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.mapper.CustomerMapper;
import com.federicobonel.restapiapplication.api.v1.model.CustomerDTO;
import com.federicobonel.restapiapplication.controllers.v1.CustomerController;
import com.federicobonel.restapiapplication.exceptions.ResourceNotFoundException;
import com.federicobonel.restapiapplication.model.Customer;
import com.federicobonel.restapiapplication.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(generateUrlForId(customerDTO.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setCustomerUrl(generateUrlForId(customerDTO.getId()));
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return saveCustomer(customerDTO);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        customerDTO.setId(id);
        return saveCustomer(customerDTO);
    }

    private CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
        CustomerDTO customerToReturn = customerMapper.customerToCustomerDTO(savedCustomer);
        customerToReturn.setCustomerUrl(generateUrlForId(customerToReturn.getId()));

        return customerToReturn;
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerToSave) {
        return customerRepository.findById(id)
                .map(customerInDB -> {
                    // Check if fields are set, if so update them and save the customer
                    if (customerToSave.getName() != null) {
                        customerInDB.setName(customerToSave.getName());
                    }

                    if (customerToSave.getLastname() != null) {
                        customerInDB.setLastname(customerToSave.getLastname());
                    }

                    CustomerDTO savedCustomer = customerMapper.customerToCustomerDTO(customerRepository.save(customerInDB));
                    savedCustomer.setCustomerUrl(generateUrlForId(id));
                    return savedCustomer;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private String generateUrlForId(Long id) {
        return CustomerController.BASE_URL_CUSTOMERS + "/" + id;
    }

}
