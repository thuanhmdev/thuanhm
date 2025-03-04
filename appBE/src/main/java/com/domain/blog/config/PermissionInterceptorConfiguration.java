package com.domain.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PermissionInterceptorConfiguration implements WebMvcConfigurer {
    @Bean
    PermissionInterceptor getPermissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] whiteList = {
                "/",
                "/blog-api/auth/admin/login",
                "/blog-api/auth/refresh",
                "/blog-api/auth/login-social-media",
                "/blog-api/blog/list",
                "/blog-api/blog/related-list",
                "/blog-api/blog/**",
                "/blog-api/blog/pagination-list",
                "/blog-api/blog/comments/**",
                "/storage/**",
                "/blog-api/auth/account",
                "/blog-api/comment/list",
                "/blog-api/comment/**",
                "/blog-api/category/list",
                "/blog-api/setting",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
        };
        registry.addInterceptor(getPermissionInterceptor())
                .excludePathPatterns(whiteList);
    }
}
