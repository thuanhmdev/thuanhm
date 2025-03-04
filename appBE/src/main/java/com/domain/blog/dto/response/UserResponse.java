package com.domain.blog.dto.response;

import com.domain.blog.constant.ProviderEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String name;
    private String image;
    private String imageProvider;
    private RoleResponse role;
    private ProviderEnum provider;

}