package com.concertai.user.management.framework.web.rbac.transformations;

import com.concertai.user.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

@Component
public class PermissionDtoTransformation implements Function<List<Permission>, Map<String, Map<String, Map<String, Boolean>>>> {
    public Map<String, Map<String, Map<String, Boolean>>> apply(List<Permission> permissions) {
        Map<String, Map<String, Map<String, Boolean>>> transformedMap = new HashMap<>();

        for (Permission permission : permissions) {
            String role = permission.getId().getRole().name().toLowerCase(Locale.ROOT);
            String resource = permission.getId().getResource();
            String action = permission.getId().getAction();

            Map<String, Map<String, Boolean>> roleMap = transformedMap.computeIfAbsent(role, k -> new HashMap<>());
            Map<String, Boolean> resourceMap = roleMap.computeIfAbsent(resource, k -> new HashMap<>());
            resourceMap.put(action, permission.isPermitted());
        }

        return transformedMap;
    }
}