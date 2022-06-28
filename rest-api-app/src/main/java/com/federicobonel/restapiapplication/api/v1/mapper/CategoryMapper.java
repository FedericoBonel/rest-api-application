package com.federicobonel.restapiapplication.api.v1.mapper;

import com.federicobonel.restapiapplication.api.v1.model.CategoryDTO;
import com.federicobonel.restapiapplication.model.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
