package com.domain.blog.service;

import com.domain.blog.entity.Blog;
import com.domain.blog.dto.response.BlogResponse;
import com.domain.blog.dto.response.PaginationListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {

    List<BlogResponse> getBlogs();

    PaginationListResponse<List<BlogResponse>> getBlogPaginationList(String keyword, String category, String sort, int page, int size);

    Blog getBlogById(Long id);

    BlogResponse getBlogResponseById(Long id);

    BlogResponse createBlog(MultipartFile file, String title, String content, String description, String keyword, String categoryId);

    BlogResponse updateBlog(MultipartFile file, Long id, String title, String content, String description, String keyword, String img, String categoryId);

    void deleteBlog(Long id);

    BlogResponse convertBlogResponse(Blog blog);

    boolean existsById(Long id);

    List<BlogResponse> getRelatedBlogs(Long id);
}