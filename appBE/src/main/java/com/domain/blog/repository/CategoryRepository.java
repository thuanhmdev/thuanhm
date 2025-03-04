package com.domain.blog.repository;

import com.domain.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);
    List<Category> findAllByOrderByPositionAsc();
}
