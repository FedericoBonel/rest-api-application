package com.federicobonel.restapiapplication.controllers.v1;

import com.federicobonel.restapiapplication.api.v1.model.CategoryDTO;
import com.federicobonel.restapiapplication.api.v1.model.CategoryListDTO;
import com.federicobonel.restapiapplication.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {
        log.info("Finding all categories...");

        return new ResponseEntity<>(
                new CategoryListDTO(categoryService.getCategories()), HttpStatus.OK
        );
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId) {
        log.info("Finding by category by id " + categoryId);

        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String categoryName) {
        log.info("Finding by category by name " + categoryName);

        return new ResponseEntity<>(categoryService.getCategoryByName(categoryName), HttpStatus.OK);
    }
}
