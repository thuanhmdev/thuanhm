package com.domain.blog.service.Impl;

import com.domain.blog.entity.Permission;
import com.domain.blog.entity.Role;
import com.domain.blog.repository.RoleRepository;
import com.domain.blog.service.PermissionService;
import com.domain.blog.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("RoleService")
@RequiredArgsConstructor
@Slf4j(topic="ROLE-SERVICE")
@Tag(name="ROLE-SERVICE")
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;

    @PostConstruct
    public void init() {
        if(roleRepository.count() == 0) {
            createAdminRole();
            createUserRole();
        }
    }


    public void createAdminRole() {
        Role adminRole = new Role();
        adminRole.setId("ADMIN");
        adminRole.setName("ADMIN");
        adminRole.setActive(true);
        List<Permission> allPermissions = permissionService.getPermissions();
        adminRole.setPermissions(allPermissions);
        roleRepository.save(adminRole);
    }

    public void createUserRole() {
        Role userRole = new Role();
        userRole.setId("USER");
        userRole.setName("USER");
        userRole.setActive(true);
        userRole.setPermissions(Arrays.asList(
                permissionService.createPermission("GET_ACCOUNT", "GET_ACCOUNT", "/blog-api/auth/account", "GET"),
                permissionService.createPermission("REFRESH_TOKEN", "REFRESH_TOKEN", "/blog-api/auth/refresh", "POST"),
                permissionService.createPermission("LOGOUT", "LOGOUT", "/blog-api/auth/logout", "POST"),
                permissionService.createPermission("LOGIN_SOCIAL_MEDIA", "LOGIN_SOCIAL_MEDIA", "/blog-api/auth/login-social-media", "POST"),

                permissionService.createPermission("GET_ALL_BLOGS", "GET_ALL_BLOGS", "/blog-api/blog/list", "GET"),
                permissionService.createPermission("GET_BLOGS_PAGINATION", "GET_BLOGS_PAGINATION", "/blog-api/blog/pagination-list", "GET"),
                permissionService.createPermission("GET_RELATED_BLOGS", "GET_RELATED_BLOGS", "/blog-api/blog/related-list", "GET"),
                permissionService.createPermission("GET_BLOG_BY_ID", "GET_BLOG_BY_ID", "/blog-api/blog/{id}", "GET"),

                permissionService.createPermission("GET_ALL_COMMENTS", "GET_ALL_COMMENTS", "/blog-api/comment/list", "GET"),
                permissionService.createPermission("GET_COMMENTS_BY_BLOG_ID", "GET_COMMENTS_BY_BLOG_ID", "/blog-api/comment/{blogId}", "GET"),
                permissionService.createPermission("CREATE_COMMENT", "CREATE_COMMENT", "/blog-api/comment/list", "POST"),

                permissionService.createPermission("GET_SETTING", "GET_SETTING", "/blog-api/setting", "GET"),
                permissionService.createPermission("GET_CONTACT_ADMIN_INFO", "GET_CONTACT_ADMIN_INFO", "/blog-api/contact", "GET")

        ));
        roleRepository.save(userRole);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
