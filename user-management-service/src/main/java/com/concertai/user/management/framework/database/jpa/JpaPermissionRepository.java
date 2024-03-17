package com.concertai.user.management.framework.database.jpa;

import com.concertai.user.entity.Permission;
import com.concertai.user.entity.PermissionKey;
import com.concertai.user.entity.resource.user.rbac.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaPermissionRepository extends JpaRepository<Permission, PermissionKey> {
    @Query("SELECT p FROM permission p WHERE p.id.role = :role")
    List<Permission> findAllByRole(@Param("role") RoleType role);

    @Query("SELECT p FROM permission p WHERE p.id.role = :role AND p.id.resource = :resource AND p.id.action = :action")
    Optional<Permission> findByRoleAndResourceAndAction(
            @Param("role") RoleType role,
            @Param("resource") String resource,
            @Param("action") String action
    );
}
