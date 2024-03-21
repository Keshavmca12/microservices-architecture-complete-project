package com.tga.search.server.config;


import com.tga.search.adapters.dto.UserInfoDTO;
import com.tga.search.adapters.security.LocalAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
    public class CustomOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    public final LocalAuthenticationService authenticationService;

    @Autowired
    public CustomOpaqueTokenIntrospector(LocalAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserInfoDTO userInfoDTO = authenticationService.introspect(token);
        Collection<GrantedAuthority> authorities = userInfoDTO
                .getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("USER_INFO_DTO", userInfoDTO);
        return new OAuth2IntrospectionAuthenticatedPrincipal(attributes, authorities);
    }
}
