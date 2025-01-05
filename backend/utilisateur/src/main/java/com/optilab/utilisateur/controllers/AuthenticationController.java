package com.optilab.utilisateur.controllers;

import com.optilab.utilisateur.dto.request.LoginRequest;
import com.optilab.utilisateur.dto.request.ResetPasswordRequest;
import com.optilab.utilisateur.dto.response.LoginResponse;
import com.optilab.utilisateur.dto.response.MessageResponse;
import com.optilab.utilisateur.services.KeycloakService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
@RequiredArgsConstructor
public class AuthenticationController {

    private final KeycloakService keycloakService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(this.keycloakService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> loginWithRefreshToken(@RequestHeader String refreshToken) {
        return ResponseEntity.ok(this.keycloakService.loginWithRefreshToken(refreshToken));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(this.keycloakService.resetPassword(resetPasswordRequest));
    }

}
