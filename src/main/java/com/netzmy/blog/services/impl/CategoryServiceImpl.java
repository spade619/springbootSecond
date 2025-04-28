package com.netzmy.blog.services.impl;

import com.netzmy.blog.domain.entities.Category;
import com.netzmy.blog.repositories.CategoryRepository;
import com.netzmy.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    public Category createCategory(Category category) {
        String categoryName = category.getName();
        if(categoryRepository.existsByNameIgnoreCase(categoryName)){
            throw new IllegalArgumentException("Category Existed: " + categoryName);
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            if(!category.get().getPosts().isEmpty()) {
                throw new IllegalStateException("Category contains post, ur not allowed to delete it.");
            }

            categoryRepository.deleteById(id);
        }
    }
}
