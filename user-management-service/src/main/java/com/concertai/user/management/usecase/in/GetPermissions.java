package com.concertai.user.management.usecase.in;

import com.concertai.user.entity.Permission;
import com.concertai.user.management.usecase.out.PermissionRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.function.Supplier;

@Named
public class GetPermissions implements Supplier<List<Permission>> {
    private final PermissionRepository repository;

    @Inject
    public GetPermissions(@Named(PermissionRepository.RDBMS) PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Permission> get() {
        return repository.getAll();
    }
}
