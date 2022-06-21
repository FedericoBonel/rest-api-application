package com.federicobonel.restapiapplication.datainitializer;

import com.federicobonel.restapiapplication.model.Category;
import com.federicobonel.restapiapplication.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            initializeCategories();
        }
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
