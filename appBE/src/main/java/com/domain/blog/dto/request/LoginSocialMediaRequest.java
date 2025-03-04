package com.domain.blog.dto.request;

import com.domain.blog.constant.ProviderEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSocialMediaRequest {

    private ProviderEnum provider;
    private String username;
    private String name;
    private String imageProvider;
}
