package com.domain.blog.service;

import com.domain.blog.dto.response.CategoryResponse;
import com.domain.blog.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findById(String id);

    CategoryResponse findCategoryResponseById(String id);

    List<CategoryResponse> getCategories();
}