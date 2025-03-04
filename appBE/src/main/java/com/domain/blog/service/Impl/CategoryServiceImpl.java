package com.domain.blog.service.Impl;

import com.domain.blog.dto.response.CategoryResponse;
import com.domain.blog.entity.Category;
import com.domain.blog.exception.DataNotFoundException;
import com.domain.blog.repository.CategoryRepository;
import com.domain.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "CATEGORY-SERVICE")
@Tag(name = "CATEGORY-SERVICE")
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        if (categoryRepository.count() == 0) {
            createCategory("React", 1, "react");
            createCategory("Spring Boot", 2, "spring-boot");
            createCategory("Khác", 3, "khac");
        }
    }


    public CategoryResponse convertCategoryResponse(Category category) {
        return CategoryResponse.builder().id(category.getId()).name(category.getName()).position(category.getPosition()).slug(category.getSlug()).build();
    }

    public void createCategory(String categoryName, int order, String slug) {
        Category category = new Category();
        category.setName(categoryName);
        category.setPosition(order);
        category.setSlug(slug);
        categoryRepository.save(category);
    }


    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Không tìm thấy danh mục"));

    }

    @Override
    public CategoryResponse findCategoryResponseById(String id) {
        return convertCategoryResponse(findById(id));
    }


    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categoryList = categoryRepository.findAllByOrderByPositionAsc();

        return categoryList.stream().map(this::convertCategoryResponse).toList();
    }
}
