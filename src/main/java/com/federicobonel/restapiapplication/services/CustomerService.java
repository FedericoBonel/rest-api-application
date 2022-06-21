package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAll();

    CustomerDTO getById(Long id);

}
