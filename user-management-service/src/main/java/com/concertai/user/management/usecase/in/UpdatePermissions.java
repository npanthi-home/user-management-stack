package com.concertai.user.management.usecase.in;

import com.concertai.user.entity.Permission;
import com.concertai.user.management.usecase.out.PermissionRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.function.Consumer;

@Named
public class UpdatePermissions implements Consumer<List<Permission>> {
    private final PermissionRepository repository;

    @Inject
    public UpdatePermissions(@Named(PermissionRepository.RDBMS) PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void accept(List<Permission> permissions) {
        repository.saveAll(permissions);
    }
}
