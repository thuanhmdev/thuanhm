package com.domain.blog.service;

import com.domain.blog.entity.Role;

public interface RoleService {

    void createAdminRole();

    void createUserRole();

    Role findByName(String name);
}