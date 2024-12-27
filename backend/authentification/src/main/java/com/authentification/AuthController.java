package com.authentification;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('client_user')")
    public String userAccess(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return String.format("Hello USER: %s", jwt.getClaim("preferred_username"));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('client_admin')")
    public String adminAccess(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return String.format("Hello ADMIN: %s", jwt.getClaim("preferred_username"));
    }

    @GetMapping("/technicien")
    @PreAuthorize("hasRole('client_technicien')")
    public String technicianAccess(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return String.format("Hello TECHNICIAN: %s", jwt.getClaim("preferred_username"));
    }

    @GetMapping("/me")
    public String getProfile(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return String.format("User: %s, Roles: %s",
                jwt.getClaim("preferred_username"),
                jwt.getClaim("realm_access"));
    }
}
