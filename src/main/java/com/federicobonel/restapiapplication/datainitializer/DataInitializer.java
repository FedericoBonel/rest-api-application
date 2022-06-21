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

        Customer customer1 = new Customer();
        customer1.setName("Federico");
        customer1.setLastname("Bonel");

        Customer customer2 = new Customer();
        customer2.setName("Haruna");
        customer2.setLastname("Takagi");

        Customer customer3 = new Customer();
        customer3.setName("Antonio");
        customer3.setLastname("Tozzi");

        customerRepository.saveAll(List.of(customer, customer1, customer2, customer3));

        log.info("Data initializer -> customers initialized!");
    }

    private void initializeCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.saveAll(List.of(fruits, dried, fresh, exotic, nuts));

        log.info("Data initializer -> categories initialized!");
    }
}
