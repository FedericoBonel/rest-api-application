package com.federicobonel.restapiapplication.api.v1.mapper;

import api.v1.model.CategoryDTO;
import com.federicobonel.restapiapplication.model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    public static final long ID = 1L;
    public static final String CATEGORY = "FRUITS";

    final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setId(ID);
        category.setName(CATEGORY);

        CategoryDTO converted = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(CATEGORY, converted.getName());
    }
}