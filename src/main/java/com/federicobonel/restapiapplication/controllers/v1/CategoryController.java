package com.federicobonel.restapiapplication.controllers.v1;

import com.federicobonel.restapiapplication.api.v1.model.CategoryDTO;
import com.federicobonel.restapiapplication.api.v1.model.CategoryListDTO;
import com.federicobonel.restapiapplication.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(CategoryController.BASE_URL_CATEGORIES)
public class CategoryController {

    public static final String BASE_URL_CATEGORIES = "/api/v1/categories";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        log.info("Finding all categories...");

        return new CategoryListDTO(categoryService.getAll());
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable Long categoryId) {
        log.info("Finding by category by id " + categoryId);

        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/name/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String categoryName) {
        log.info("Finding by category by name " + categoryName);

        return categoryService.getCategoryByName(categoryName);
    }
}
