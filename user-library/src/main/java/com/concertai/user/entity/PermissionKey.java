package com.concertai.user.entity;

import com.concertai.user.entity.resource.user.rbac.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PermissionKey implements Serializable {
    @Enumerated(EnumType.STRING)
    private RoleType role;
    private String resource;
    private String action;
}
