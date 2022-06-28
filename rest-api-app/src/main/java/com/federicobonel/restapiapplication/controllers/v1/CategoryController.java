package com.federicobonel.restapiapplication.controllers.v1;


import api.v1.model.CategoryDTO;
import api.v1.model.CategoryListDTO;
import com.federicobonel.restapiapplication.config.SwaggerConfig;
import com.federicobonel.restapiapplication.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {SwaggerConfig.CATEGORY_TAG})
@RestController
@RequestMapping(CategoryController.BASE_URL_CATEGORIES)
public class CategoryController {

    public static final String BASE_URL_CATEGORIES = "/api/v1/categories";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Gets all categories")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        log.info("Finding all categories...");

        CategoryListDTO list = new CategoryListDTO();
        list.getCategories().addAll(categoryService.getAll());
        return list;
    }

    @ApiOperation(value = "Gets the category with the given ID", notes = "If no category is found 404 code is returned")
    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable Long categoryId) {
        log.info("Finding by category by id " + categoryId);
        CategoryDTO found = categoryService.getCategoryById(categoryId);
        return found;
    }

    @ApiOperation(value = "Gets the category with the given Name", notes = "If no category is found 404 code is returned")
    @GetMapping("/name/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String categoryName) {
        log.info("Finding by category by name " + categoryName);

        return categoryService.getCategoryByName(categoryName);
    }
}
