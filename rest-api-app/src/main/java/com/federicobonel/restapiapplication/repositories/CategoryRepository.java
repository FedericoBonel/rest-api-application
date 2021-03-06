package com.federicobonel.restapiapplication.repositories;

import com.federicobonel.restapiapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByNameContainingIgnoreCase(String name);
}
