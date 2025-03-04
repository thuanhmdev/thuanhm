package com.domain.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class BlogResponse {
    private Long id;
    private String title;
    private String description;
    private String keyword;
    private String image;
    private String content;
    private UserResponse blogger;
    private CategoryResponse category;
    private Long createdAt;
    private Long updatedAt;
}