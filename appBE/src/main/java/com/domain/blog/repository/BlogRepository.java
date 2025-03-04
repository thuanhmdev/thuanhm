package com.domain.blog.repository;

import com.domain.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    List<Blog> findAllByOrderByCreatedAtDesc();

    @Query(value="select u from Blog u where lower(u.title) like %:keyword%")
    Page<Blog> getPaginationListByKeyword(String keyword, Pageable pageable);

    @Query(value="select u from Blog u where lower(u.title) like %:keyword% and u.category.slug= :category")
    Page<Blog> getPaginationListByKeywordAndCategory(String keyword, String category, Pageable pageable);

    @Query(value="select u from Blog u where u.category.slug= :category")
    Page<Blog> getPaginationListByCategory(String category, Pageable pageable);


    List<Blog> findTop5ByCategoryIdOrderByCreatedAtDesc(String id);
}
