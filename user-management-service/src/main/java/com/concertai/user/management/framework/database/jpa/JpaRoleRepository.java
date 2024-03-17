package com.concertai.user.management.framework.database.jpa;

import com.concertai.user.entity.resource.user.rbac.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<Role, String> {
}
