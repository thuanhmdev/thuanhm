package com.domain.blog.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RoleResponse {
    private String id;
    private String name;
}

