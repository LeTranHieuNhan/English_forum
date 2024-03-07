package org.example.englishforum.service;

import org.example.englishforum.dto.CategoryDto;
import org.example.englishforum.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAllCategories();
    CategoryDto findCategoryById (Long id);
    CategoryDto createCategory(String name);
    void deleteCategory(Long id);
    CategoryDto udpateCategory(Long id, String name);
}
