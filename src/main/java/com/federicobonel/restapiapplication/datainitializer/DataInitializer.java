package com.federicobonel.restapiapplication.datainitializer;

import com.federicobonel.restapiapplication.model.Category;
import com.federicobonel.restapiapplication.model.Customer;
import com.federicobonel.restapiapplication.repositories.CategoryRepository;
import com.federicobonel.restapiapplication.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public DataInitializer(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            initializeCategories();
        }
        if (customerRepository.count() == 0) {
            initializeCustomers();
        }
    }

    private void initializeCustomers() {
        Customer customer = new Customer();
        customer.setName("Name");
        customer.setLastname("Lastname");
        customer.setCustomerUrl("https://customerExample.com");

        Customer customer1 = new Customer();
        customer1.setName("Federico");
        customer1.setLastname("Bonel");
        customer1.setCustomerUrl("https://customerExample.com");

        Customer customer2 = new Customer();
        customer2.setName("Haruna");
        customer2.setLastname("Takagi");
        customer2.setCustomerUrl("https://customerExample.com");

        Customer customer3 = new Customer();
        customer3.setName("Antonio");
        customer3.setLastname("Tozzi");
        customer3.setCustomerUrl("https://customerExample.com");

        customerRepository.saveAll(List.of(customer, customer1, customer2, customer3));

        log.info("Data initializer -> customers initialized!");
    }

    private void initializeCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");
        fruits.setCategoryUrl("/shop/categories/Fruits");

        Category dried = new Category();
        dried.setName("Dried");
        dried.setCategoryUrl("/shop/categories/Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");
        fresh.setCategoryUrl("/shop/categories/Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");
        exotic.setCategoryUrl("/shop/categories/Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");
        nuts.setCategoryUrl("/shop/categories/Nuts");

        categoryRepository.saveAll(List.of(fruits, dried, fresh, exotic, nuts));

        log.info("Data initializer -> categories initialized!");
    }
}
