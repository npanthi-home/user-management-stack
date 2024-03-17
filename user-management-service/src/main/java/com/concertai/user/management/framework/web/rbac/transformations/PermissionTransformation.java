package com.concertai.user.management.framework.web.rbac.transformations;

import com.concertai.user.entity.Permission;
import com.concertai.user.entity.PermissionKey;
import com.concertai.user.entity.resource.user.rbac.RoleType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PermissionTransformation {
    public List<Permission> apply(HashMap<String, HashMap<String, HashMap<String, Boolean>>> transformedMap) {
        List<Permission> permissions = new ArrayList<>();

        for (Map.Entry<String, HashMap<String, HashMap<String, Boolean>>> roleEntry : transformedMap.entrySet()) {
            String role = roleEntry.getKey();

            for (Map.Entry<String, HashMap<String, Boolean>> resourceEntry : roleEntry.getValue().entrySet()) {
                String resource = resourceEntry.getKey();

                for (Map.Entry<String, Boolean> actionEntry : resourceEntry.getValue().entrySet()) {
                    String action = actionEntry.getKey();
                    boolean isPermitted = actionEntry.getValue();
                    permissions.add(createPermission(role, resource, action, isPermitted));
                }
            }
        }

        return permissions;
    }

    private static Permission createPermission(String role, String resource, String action, boolean isPermitted) {
        PermissionKey permissionKey = new PermissionKey(RoleType.valueOf(role.toUpperCase()), resource, action);
        return new Permission(permissionKey, isPermitted);
    }
}
