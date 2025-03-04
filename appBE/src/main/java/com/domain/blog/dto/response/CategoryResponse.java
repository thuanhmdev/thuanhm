package com.domain.blog.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {
    private String id;
    private String name;
    private int position;
    private String slug;

}
