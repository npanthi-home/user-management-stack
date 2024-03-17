package com.concertai.user.management.adapter.in.http;

import com.concertai.user.entity.resource.user.rbac.RoleType;
import com.concertai.user.management.framework.web.rbac.transformations.PermissionDtoTransformation;
import com.concertai.user.management.framework.web.rbac.transformations.PermissionTransformation;
import com.concertai.user.management.usecase.in.GetPermissions;
import com.concertai.user.management.usecase.in.GetPermissionsByRole;
import com.concertai.user.management.usecase.in.UpdatePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rbac")
public class RbacController {
    private final GetPermissions getPermissions;
    private final GetPermissionsByRole getPermissionsByRole;
    private final UpdatePermissions updatePermissions;
    private final PermissionDtoTransformation dtoTransformation;
    private final PermissionTransformation modelTransformation;

    @Autowired
    public RbacController(
            GetPermissions getPermissions,
            GetPermissionsByRole getPermissionsByRole,
            UpdatePermissions updatePermissions,
            PermissionDtoTransformation dtoTransformation,
            PermissionTransformation modelTransformation
    ) {
        this.getPermissions = getPermissions;
        this.getPermissionsByRole = getPermissionsByRole;
        this.updatePermissions = updatePermissions;
        this.dtoTransformation = dtoTransformation;
        this.modelTransformation = modelTransformation;
    }

    @GetMapping("/role/{roleType}")
    public Map<String, Map<String, Map<String, Boolean>>> getPermissions(@PathVariable("roleType") String roleType) {
        return dtoTransformation.apply(getPermissionsByRole.apply(RoleType.valueOf(roleType.toUpperCase())));
    }

    @GetMapping
    public Map<String, Map<String, Map<String, Boolean>>> getPermissions() {
        return dtoTransformation.apply(getPermissions.get());
    }

    @PatchMapping
    public Map<String, Map<String, Map<String, Boolean>>> updatePermissions(@RequestBody HashMap<String, HashMap<String, HashMap<String, Boolean>>> dto) {
        updatePermissions.accept(modelTransformation.apply(dto));
        return getPermissions();
    }
}
