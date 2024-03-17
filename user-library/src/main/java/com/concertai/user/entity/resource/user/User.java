package com.concertai.user.entity.resource.user;

import com.concertai.user.entity.resource.Resource;
import com.concertai.user.entity.resource.user.rbac.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Entity(name = "rbac_user")
@Table(name = "rbac_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Resource, Serializable {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;
    @JoinColumn(name = "role", referencedColumnName = "name")
    private Role role;

    @Override
    public String getName() {
        return "user";
    }
}
