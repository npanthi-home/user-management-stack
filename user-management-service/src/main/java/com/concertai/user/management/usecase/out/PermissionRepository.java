package com.concertai.user.management.usecase.out;

import com.concertai.user.entity.Permission;
import com.concertai.user.entity.resource.user.rbac.RoleType;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository {
    String RDBMS = "com.concertai.user.management.usecase.out.PermissionRepository.RDBMS";
    List<Permission> getAll();
    List<Permission> get(RoleType role);
    Optional<Permission> get(RoleType role, String resource, String action);
    void saveAll(List<Permission> permissions);
}
