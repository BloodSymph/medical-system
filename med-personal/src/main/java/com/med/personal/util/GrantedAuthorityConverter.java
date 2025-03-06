package com.med.personal.util;

import io.jsonwebtoken.Claims;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class GrantedAuthorityConverter {

    public static Collection<? extends GrantedAuthority> convert(Claims claims) {
        List<?> claimsList = (List<?>) claims.get("roles");
        return claimsList.stream()
                .map(claim -> (LinkedHashMap<String, String>) claim)
                .map(linkedHashMap -> new SimpleGrantedAuthority(
                        linkedHashMap.get("authority")
                ))
                .collect(Collectors.toList());
    }

}
