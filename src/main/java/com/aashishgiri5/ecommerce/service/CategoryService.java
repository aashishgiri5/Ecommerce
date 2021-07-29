package com.aashishgiri5.ecommerce.service;

import com.aashishgiri5.ecommerce.dto.CategoryRequest;
import com.aashishgiri5.ecommerce.dto.OrderRequest;
import com.aashishgiri5.ecommerce.model.Category;
import com.aashishgiri5.ecommerce.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;



    public void createType(CategoryRequest categoryRequest) {
        Category category=new Category();
        category.setType(categoryRequest.getType());
        category.setCreatedBy(categoryRequest.getCreatedBy());
        categoryRepository.save(category);

    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    public void update(Category category, CategoryRequest categoryRequest) {
        category.setCreatedBy(categoryRequest.getCreatedBy());
        category.setType(categoryRequest.getType());
    }

    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }
}
