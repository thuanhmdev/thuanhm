package com.domain.blog.repository;

import com.domain.blog.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
