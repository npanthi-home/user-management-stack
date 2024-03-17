package com.concertai.user.management.usecase.in;

import com.concertai.user.entity.Permission;
import com.concertai.user.entity.resource.user.rbac.RoleType;
import com.concertai.user.management.usecase.out.PermissionRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.function.Function;

@Named
public class GetPermissionsByRole implements Function<RoleType, List<Permission>> {
    private final PermissionRepository repository;

    @Inject
    public GetPermissionsByRole(@Named(PermissionRepository.RDBMS) PermissionRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Permission> apply(RoleType role) {
        return repository.get(role);
    }
}
