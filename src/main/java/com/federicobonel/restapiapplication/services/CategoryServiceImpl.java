package com.federicobonel.restapiapplication.services;

import com.federicobonel.restapiapplication.api.v1.mapper.CategoryMapper;
import com.federicobonel.restapiapplication.api.v1.model.CategoryDTO;
import com.federicobonel.restapiapplication.model.Category;
import com.federicobonel.restapiapplication.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final static String BASE_URL = "/api/v1/categories/";

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> {
                    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
                    categoryDTO.setCategoryUrl(BASE_URL + category.getId());
                    return categoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        CategoryDTO categoryDTO = categoryMapper
                .categoryToCategoryDTO(categoryRepository.findByNameContainingIgnoreCase(name));
        categoryDTO.setCategoryUrl(BASE_URL + categoryDTO.getId());

        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::categoryToCategoryDTO)
                .map(categoryDTO -> {
                    categoryDTO.setCategoryUrl(BASE_URL + categoryDTO.getId());
                    return categoryDTO;
                })
                .orElse(null);
    }
}
