package com.federicobonel.restapiapplication.datainitializer;

import com.federicobonel.restapiapplication.model.Category;
import com.federicobonel.restapiapplication.model.Customer;
import com.federicobonel.restapiapplication.model.Vendor;
import com.federicobonel.restapiapplication.repositories.CategoryRepository;
import com.federicobonel.restapiapplication.repositories.CustomerRepository;
import com.federicobonel.restapiapplication.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public DataInitializer(CategoryRepository categoryRepository,
                           CustomerRepository customerRepository,
                           VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            initializeCategories();
        }
        if (customerRepository.count() == 0) {
            initializeCustomers();
        }
        if (vendorRepository.count() == 0) {
            initializeVendors();
        }
    }

    private void initializeVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Carrefour");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Disco");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Naniwaya");

        Vendor vendor4 = new Vendor();
        vendor4.setName("Hanamasa");

        vendorRepository.saveAll(List.of(vendor1, vendor2, vendor3, vendor4));

        log.info("Data initializer -> vendors initialized!");
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
