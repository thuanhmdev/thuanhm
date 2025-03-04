package com.domain.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PageResponse<T> {
    private int status;
    private String message;
    private T data;
}
