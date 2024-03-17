package com.concertai.user.entity.token;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class SecurityAspect {
    private final VerifyToken verifyToken;

    @Autowired
    public SecurityAspect(VerifyToken verifyToken) {
        this.verifyToken = verifyToken;
    }

    @SneakyThrows
    @Before("@annotation(securedAnnotation)")
    public void checkPermissions(JoinPoint joinPoint, Secured securedAnnotation) {
        String[] requiredPermissions = securedAnnotation.permissions();
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof String) {
                String jwtToken = (String) arg;
                if (jwtToken.startsWith("Bearer ")) {
                    jwtToken = jwtToken.substring(7);
                    if (hasRequiredPermissions(jwtToken, requiredPermissions)) {
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException("Insufficient permissions.");
    }

    @SneakyThrows
    private boolean hasRequiredPermissions(String jwtToken, String[] requiredPermissions) {
        Map<String, Set<String>> permittedActionsByResource = verifyToken.apply(jwtToken);
        for (String requiredPermission : requiredPermissions) {
            String[] parts = requiredPermission.split(":");
            if (parts.length != 2) {
                throw new AccessDeniedException("Invalid permission format: " + requiredPermission);
            }
            String resource = parts[0];
            String[] actions = parts[1].split(",");
            Set<String> permittedActions = permittedActionsByResource.get(resource);
            boolean isPermitted = permittedActions != null && Arrays.stream(actions).anyMatch(permittedActions::contains);
            if (isPermitted) {
                return true;
            }
        }
        return false;
    }
}