package com.concertai.user.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Entity(name = "permission")
@Table(name = "permission")
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
    @EmbeddedId
    private PermissionKey id;
    private boolean isPermitted;
}
