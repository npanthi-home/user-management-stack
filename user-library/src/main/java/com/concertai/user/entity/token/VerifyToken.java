package com.concertai.user.entity.token;


import com.google.common.collect.Sets;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.inject.Named;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Named
public class VerifyToken implements Function<String, Map<String, Set<String>>> {
    @Value("${user.token.secret:ThisIsASecretKeyForTestingPurposes123456}")
    private String secret;

    @SneakyThrows
    public Map<String, Set<String>> apply(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secret);

            if (isValidSignature(signedJWT, verifier)) {
                JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
                Date now = new Date();

                if (isTokenExpired(claimsSet, now)) {
                    log.error("Token expired.");
                    return Collections.emptyMap();
                }

                return getPermissionsFromClaims(claimsSet);
            } else {
                log.error("Token signature verification failed.");
            }
        } catch (ParseException | JOSEException e) {
            log.error("Failed to parse or verify token: {}", e.getMessage());
        }
        return Collections.emptyMap();
    }

    private static Map<String, Set<String>> getPermissionsFromClaims(JWTClaimsSet claimsSet) {
        Map<String, Set<String>> actionsByResource = new HashMap<>();
        for (Map.Entry<String, Object> entry : claimsSet.getClaims().entrySet()) {
            if (isResourceClaim(entry)) {
                actionsByResource.put(entry.getKey(), getActions(entry));
            }
        }
        return actionsByResource;
    }

    private static boolean isValidSignature(SignedJWT signedJWT, JWSVerifier verifier) throws JOSEException {
        return signedJWT.verify(verifier);
    }

    private static boolean isTokenExpired(JWTClaimsSet claimsSet, Date now) {
        return claimsSet.getExpirationTime() != null && claimsSet.getExpirationTime().before(now);
    }

    private static boolean isResourceClaim(Map.Entry<String, Object> entry) {
        return !entry.getKey().equals("username")
                && !entry.getKey().equals("iss")
                && !entry.getKey().equals("sub")
                && !entry.getKey().equals("iat")
                && !entry.getKey().equals("exp");
    }

    private static Set<String> getActions(Map.Entry<String, Object> entry) {
        String[] actions = ((String) entry.getValue()).split(",");
        return actions != null ? Sets.newHashSet(actions) : new HashSet<>();
    }
}
