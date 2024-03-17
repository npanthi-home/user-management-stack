package com.concertai.user.management.adapter.out.postgres;

import com.concertai.user.entity.Permission;
import com.concertai.user.entity.resource.user.rbac.RoleType;
import com.concertai.user.management.framework.database.jpa.JpaPermissionRepository;
import com.concertai.user.management.usecase.out.PermissionRepository;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Named(PermissionRepository.RDBMS)
public class PostgresPermissionRepository implements PermissionRepository {
    private final JpaPermissionRepository jpaPermissionRepository;

    @Autowired
    public PostgresPermissionRepository(JpaPermissionRepository jpaPermissionRepository) {
        this.jpaPermissionRepository = jpaPermissionRepository;
    }

    @Override
    public List<Permission> getAll() {
        return jpaPermissionRepository.findAll();
    }

    @Override
    public List<Permission> get(RoleType role) {
        return jpaPermissionRepository.findAllByRole(role);
    }

    @Override
    public Optional<Permission> get(RoleType role, String resource, String action) {
        return jpaPermissionRepository.findByRoleAndResourceAndAction(role, resource, action);
    }

    @Override
    public void saveAll(List<Permission> permissions) {
        jpaPermissionRepository.saveAll(permissions);
    }
}
