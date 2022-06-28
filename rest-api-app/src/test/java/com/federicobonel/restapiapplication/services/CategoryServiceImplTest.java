package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.mapper.CategoryMapper;
import com.federicobonel.restapiapplication.api.v1.model.CategoryDTO;
import com.federicobonel.restapiapplication.controllers.v1.CategoryController;
import com.federicobonel.restapiapplication.model.Category;
import com.federicobonel.restapiapplication.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    public static final long ID = 1L;
    public static final String CATEGORY_NAME = "Category name";
    public static final String CATEGORY_URL = CategoryController.BASE_URL_CATEGORIES + "/" + ID;

    @Mock
    CategoryRepository categoryRepository;
    CategoryService categoryService;
    CategoryMapper categoryMapper;

    Category category;
    List<Category> categories;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryMapper = CategoryMapper.INSTANCE;
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);

        category = new Category();
        category.setId(ID);
        category.setName(CATEGORY_NAME);

        categories = List.of(new Category(), new Category(), new Category());
    }

    @Test
    void getCategories() {
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoriesFound = categoryService.getAll();

        assertEquals(categories.size(), categoriesFound.size());
        verify(categoryRepository).findAll();
    }

    @Test
    void getCategoryByName() {
        when(categoryRepository.findByNameContainingIgnoreCase(CATEGORY_NAME)).thenReturn(category);

        CategoryDTO foundCategory = categoryService.getCategoryByName(CATEGORY_NAME);

        assertEquals(CATEGORY_NAME, foundCategory.getName());
        assertEquals(CATEGORY_URL, foundCategory.getCategoryUrl());
    }

    @Test
    void getCategoryById() {
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));

        CategoryDTO foundCategory = categoryService.getCategoryById(ID);

        assertEquals(CATEGORY_URL, foundCategory.getCategoryUrl());
    }
}