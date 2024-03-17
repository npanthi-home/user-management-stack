package com.concertai.user.management.usecase.in;

import com.concertai.user.entity.resource.user.User;
import com.concertai.user.entity.resource.user.rbac.Role;
import com.concertai.user.entity.resource.user.rbac.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GetTokenByUsername implements Function<String, String> {
    private final GetTokenByUsernameAndRole getTokenByUsernameAndRole;

    @Autowired
    public GetTokenByUsername(GetTokenByUsernameAndRole getTokenByUsernameAndRole) {
        this.getTokenByUsernameAndRole = getTokenByUsernameAndRole;
    }

    @Override
    public String apply(String username) {
        User user = User.builder()
                .username(username)
                .role(Role.builder().name(RoleType.WORKER).build())
                .build(); //TODO retrieve user by username from database.
        return getTokenByUsernameAndRole.apply(user.getUsername(), user.getRole().getName());
    }
}
