package com.federicobonel.restapiapplication.controllers.v1;

import com.federicobonel.restapiapplication.api.v1.model.CategoryDTO;
import com.federicobonel.restapiapplication.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    public static final Long ID = 1L;
    public static final String CATEGORY_NAME = "Category name";
    public static final String CATEGORY_URL = CategoryController.BASE_URL_CATEGORIES + "/" + ID;
    public static final String CATEGORY_URL_NAME = CategoryController.BASE_URL_CATEGORIES + "/name/" + CATEGORY_NAME;

    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    CategoryDTO category;
    List<CategoryDTO> categories;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

        category = new CategoryDTO();
        category.setName(CATEGORY_NAME);
        category.setCategoryUrl(CATEGORY_URL);

        categories = List.of(new CategoryDTO(), new CategoryDTO(), new CategoryDTO());
    }

    @Test
    void getAllCategories() throws Exception {
        when(categoryService.getAll()).thenReturn(categories);

        mockMvc.perform(get(CategoryController.BASE_URL_CATEGORIES))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(categories.size())));

        verify(categoryService).getAll();
    }

    @Test
    void getCategoryById() throws Exception {
        when(categoryService.getCategoryById(ID)).thenReturn(category);

        mockMvc.perform(get(CATEGORY_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category_url", equalTo(CATEGORY_URL)));

        verify(categoryService).getCategoryById(ID);
    }

    @Test
    void getCategoryByName() throws Exception {
        when(categoryService.getCategoryByName(CATEGORY_NAME)).thenReturn(category);

        mockMvc.perform(get(CATEGORY_URL_NAME))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(CATEGORY_NAME)));

        verify(categoryService).getCategoryByName(CATEGORY_NAME);
    }
}