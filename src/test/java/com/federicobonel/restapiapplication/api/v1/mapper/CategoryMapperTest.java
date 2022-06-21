package com.federicobonel.restapiapplication.api.v1.mapper;

import com.federicobonel.restapiapplication.api.v1.model.CategoryDTO;
import com.federicobonel.restapiapplication.model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final long ID = 1L;
    public static final String CATEGORY = "FRUITS";
    public static final String EXAMPLE_URL = "https://example.com";

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setId(ID);
        category.setName(CATEGORY);
        category.setCategoryUrl(EXAMPLE_URL);

        CategoryDTO converted = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(ID, converted.getId());
        assertEquals(CATEGORY, converted.getName());
        assertEquals(EXAMPLE_URL, converted.getCategoryUrl());
    }
}