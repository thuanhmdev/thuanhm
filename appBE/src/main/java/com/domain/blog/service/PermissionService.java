package com.domain.blog.service;

import com.domain.blog.entity.Permission;

import java.util.List;

public interface PermissionService {

    Permission createPermission(String id, String name, String apiPath, String method);

    List<Permission> getPermissions();
}