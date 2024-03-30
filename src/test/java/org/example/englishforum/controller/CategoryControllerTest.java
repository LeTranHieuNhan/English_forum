package org.example.englishforum.controller;

import org.example.englishforum.dto.CategoryDto;
import org.example.englishforum.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get all categories and return ok status")
    void getAllCategories() {
        List<CategoryDto> categoryDtoList = Collections.singletonList(new CategoryDto());
        when(categoryService.findAllCategories()).thenReturn(categoryDtoList);

        ResponseEntity<List<CategoryDto>> response = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDtoList, response.getBody());
    }

    @Test
    @DisplayName("Should get category by id and return ok status")
    void getCategoryById() {
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.findCategoryById(1L)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    @DisplayName("Should create category and return created status")
    void createCategory() {
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.createCategory("Test")).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.createCategory("Test");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    @DisplayName("Should update category and return ok status")
    void updateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.udpateCategory(1L, "Test")).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.updateCategory(1L, "Test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryDto, response.getBody());
    }

    @Test
    @DisplayName("Should delete category and return no content status")
    void deleteCategory() {
        doNothing().when(categoryService).deleteCategory(1L);

        ResponseEntity<Void> response = categoryController.deleteCategory(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}