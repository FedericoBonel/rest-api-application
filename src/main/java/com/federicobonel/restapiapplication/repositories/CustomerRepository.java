package com.federicobonel.restapiapplication.repositories;

import com.federicobonel.restapiapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
