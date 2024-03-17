package com.concertai.user.entity.resource.user.rbac;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Entity(name = "role")
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleType name;
}
