package com.concertai.user.management.usecase.in;

import com.concertai.user.entity.Permission;
import com.concertai.user.entity.resource.user.rbac.RoleType;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GetTokenByUsernameAndRole implements BiFunction<String, RoleType, String> {
    @Value("${user.token.secret:ThisIsASecretKeyForTestingPurposes123456}")
    private String secret;

    @Value("${user.token.expiration_millis:3600000}")
    private Long expirationInMilliseconds;

    private final GetPermissionsByRole getPermissionsByRole;

    @Autowired
    public GetTokenByUsernameAndRole(GetPermissionsByRole getPermissionsByRole) {
        this.getPermissionsByRole = getPermissionsByRole;
    }

    @SneakyThrows
    @Override
    public String apply(String username, RoleType role) {
        Date now = new Date();
        JWSHeader header = getHeader();
        JWTClaimsSet jwtClaims = getClaims(username, role, now);
        return getSignedJwt(header, jwtClaims);
    }

    private static JWSHeader getHeader() {
        return new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();
    }

    private JWTClaimsSet getClaims(String username, RoleType role, Date now) {
        Map<String, List<String>> allowedActionsByResource = getAllowedActionsByResource(username, role);
        JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
                .issuer("your-issuer")
                .subject("subject")
                .issueTime(now)
                .expirationTime(getExpirationTime(now))
                .claim("username", username);
        allowedActionsByResource
                .forEach((resource, actions) -> claimsBuilder.claim(resource, StringUtils.join(actions, ",")));
        return claimsBuilder.build();
    }

    private Map<String, List<String>> getAllowedActionsByResource(String username, RoleType role) {
        Map<String, List<String>> allowedActionsByResource =
                getPermissionsByRole.apply(role).stream()
                        .filter(Permission::isPermitted)
                        .collect(Collectors.groupingBy(
                                permission -> permission.getId().getResource(),
                                Collectors.mapping(permission -> permission.getId().getAction(), Collectors.toList())
                        ));
        if (allowedActionsByResource.isEmpty()) {
            log.warn("No permissions found for the role {} on user {}.", role.name(), username);
        }
        return allowedActionsByResource;
    }

    private Date getExpirationTime(Date now) {
        long expMillis = now.getTime() + expirationInMilliseconds;
        return new Date(expMillis);
    }

    private String getSignedJwt(JWSHeader header, JWTClaimsSet jwtClaims) throws JOSEException {
        SignedJWT signedJWT = new SignedJWT(header, jwtClaims);
        JWSSigner signer = new MACSigner(secret);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }
}
