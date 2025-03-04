package com.domain.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogRequest {
    private Long id;

    @NotBlank(message = "Tiêu đề không được bỏ trống")
    private String title;

    private String description;
    private String keyword;
    private String image;

    @NotBlank(message = "Nội dung không được bỏ trống")
    private String content;
    private String userId;
    private String categoryId;
    private Long createdAt;
    private Long updatedAt;

}