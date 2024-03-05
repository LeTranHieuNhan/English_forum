package org.example.englishforum.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.CategoryDto;
import org.example.englishforum.entity.Category;
import org.example.englishforum.entity.Post;
import org.example.englishforum.repository.CategoryRepository;
import org.example.englishforum.repository.PostRepository;
import org.example.englishforum.service.CategoryService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final GenericMapper genericMapper;

    @Override
    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return genericMapper.mapList(categories, CategoryDto.class);
    }


    @Override
    public CategoryDto findCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(!category.isPresent()) {
            throw new RuntimeException("Category does not exist");
        }
        return genericMapper.map(category, CategoryDto.class);
    }


    @Override
    @Transactional
    public CategoryDto createCategory(String name) {
        Category category = new Category();

        category.setName(name);
        Category savedCategory = categoryRepository.save(category);

        return genericMapper.map(savedCategory, CategoryDto.class);
    }


    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(!category.isPresent()) {
            throw new RuntimeException("Category does not exist");
        }

        List<Post> posts = category.get().getPosts();

        posts.forEach(post -> post.setCategory(null));

        categoryRepository.deleteById(id);
        postRepository.saveAll(posts);
    }


    @Override
    @Transactional
    public CategoryDto udpateCategory(Long id, String name) {
        Optional<Category> category = categoryRepository.findById(id);

        if(!category.isPresent()) {
            throw new RuntimeException("Category does not exist");
        }

        Category existCategory = category.get();
        existCategory.setName(name);

        Category savedCategory = categoryRepository.save(existCategory);
        return genericMapper.map(savedCategory, CategoryDto.class);
    }
}
