package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.mapper.CategoryMapper;
import com.federicobonel.restapiapplication.api.v1.model.CategoryDTO;
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
    public static final String URL = "https://example.com";

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
        category.setCategoryUrl(URL);

        categories = List.of(new Category(), new Category(), new Category());
    }

    @Test
    void getCategories() {
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoriesFound = categoryService.getCategories();

        assertEquals(categories.size(), categoriesFound.size());
        verify(categoryRepository).findAll();
    }

    @Test
    void getCategoryByName() {
        when(categoryRepository.findByNameContainingIgnoreCase(CATEGORY_NAME)).thenReturn(category);

        assertEquals(CATEGORY_NAME, categoryService.getCategoryByName(CATEGORY_NAME).getName());
    }

    @Test
    void getCategoryById() {
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));

        assertEquals(ID, categoryService.getCategoryById(ID).getId());
    }
}