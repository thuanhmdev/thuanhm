package com.domain.blog.service.Impl;


import com.domain.blog.entity.Permission;
import com.domain.blog.repository.PermissionRepository;
import com.domain.blog.service.PermissionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service("PermissionService")
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;


    public Permission createPermission(String id, String name, String apiPath, String method) {
        Permission permission = new Permission();
        permission.setId(id);
        permission.setName(name);
        permission.setApiPath(apiPath);
        permission.setMethod(method);
        permission.setActive(true);
        return permission;
    }

    @PostConstruct
    public void initializePermission() {
        if (permissionRepository.count() == 0) {
            List<Permission> permissions = Arrays.asList(
                    createPermission("READ_USER", "READ_USER", "/blog-api/user/{id}", "GET"),
                    createPermission("UPDATE_USER", "UPDATE_USER", "/blog-api/user/list", "PUT"),

                    createPermission("LOGIN_ADMIN", "LOGIN_ADMIN", "/blog-api/auth/admin/login", "POST"),
                    createPermission("GET_ACCOUNT", "GET_ACCOUNT", "/blog-api/auth/account", "GET"),
                    createPermission("REFRESH_TOKEN", "REFRESH_TOKEN", "/blog-api/auth/refresh", "POST"),
                    createPermission("LOGOUT", "LOGOUT", "/blog-api/auth/logout", "POST"),
                    createPermission("LOGIN_SOCIAL_MEDIA", "LOGIN_SOCIAL_MEDIA", "/blog-api/auth/login-social-media", "POST"),

                    createPermission("GET_ALL_BLOGS", "GET_ALL_BLOGS", "/blog-api/blog/list", "GET"),
                    createPermission("GET_BLOGS_PAGINATION", "GET_BLOGS_PAGINATION", "/blog-api/blog/pagination-list", "GET"),
                    createPermission("GET_RELATED_BLOGS", "GET_RELATED_BLOGS", "/blog-api/blog/related-list", "GET"),
                    createPermission("GET_BLOG_BY_ID", "GET_BLOG_BY_ID", "/blog-api/blog/{id}", "GET"),
                    createPermission("CREATE_BLOG", "CREATE_BLOG", "/blog-api/blog/create", "POST"),
                    createPermission("UPDATE_BLOG", "UPDATE_BLOG", "/blog-api/blog/update", "PUT"),
                    createPermission("DELETE_BLOG", "DELETE_BLOG", "/blog-api/blog/{id}", "DELETE"),

                    createPermission("GET_ALL_COMMENTS", "GET_ALL_COMMENTS", "/blog-api/comment/list", "GET"),
                    createPermission("GET_COMMENTS_BY_BLOG_ID", "GET_COMMENTS_BY_BLOG_ID", "/blog-api/comment/{blogId}", "GET"),
                    createPermission("CREATE_COMMENT", "CREATE_COMMENT", "/blog-api/comment/create", "POST"),
                    createPermission("DELETE_COMMENT", "DELETE_COMMENT", "/blog-api/comment/{id}", "DELETE"),

                    createPermission("GET_SETTING", "GET_SETTING", "/blog-api/setting", "GET"),
                    createPermission("GET_CONTACT_ADMIN_INFO", "GET_CONTACT_ADMIN_INFO", "/blog-api/contact", "GET"),
                    createPermission("UPDATE_SETTING", "UPDATE_SETTING", "/blog-api/setting", "PUT")
            );

            permissionRepository.saveAll(permissions);
        }
    }

    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

}
