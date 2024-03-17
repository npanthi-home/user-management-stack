package com.concertai.user.management.usecase.in;

import com.concertai.user.entity.resource.user.rbac.RoleType;
import com.concertai.user.management.usecase.out.PermissionRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class IsPermitted {
    private final PermissionRepository repository;

    @Inject
    public IsPermitted(@Named(PermissionRepository.RDBMS) PermissionRepository repository) {
        this.repository = repository;
    }

    public boolean test(RoleType role, String resource, String action) {
        return repository.get(role, resource, action).isPresent();
    }
}
