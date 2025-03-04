package com.domain.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PaginationListResponse<T> implements Serializable {
    private int page;
    private int size;
    private long totalPages;
    private long totalElements;
    private T data;
}
