package com.federicobonel.restapiapplication.services;


import api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAll();

    CategoryDTO getCategoryByName(String name);

    CategoryDTO getCategoryById(Long id);
}
