package com.optilab.utilisateur.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optilab.utilisateur.dto.request.LoginRequest;
import com.optilab.utilisateur.dto.request.ResetPasswordRequest;
import com.optilab.utilisateur.dto.request.RoleRequest;
import com.optilab.utilisateur.dto.request.UtilisateurRequest;
import com.optilab.utilisateur.dto.response.LoginResponse;
import com.optilab.utilisateur.dto.response.MessageResponse;
import com.optilab.utilisateur.exception.NotFoundException;
import com.optilab.utilisateur.exception.TechnicalException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakService {

    private final Keycloak keycloak;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String REFRESH_TOKEN = "refresh_token";
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.auth-url}")
    private String authUrl;
    @Value("${keycloak.client-id}")
    private String clientId;
    @Value("${keycloak.client-secret}")
    private String clientSecret;

    public LoginResponse login(LoginRequest loginRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("username", loginRequest.getUsername());
        map.add("password", loginRequest.getPassword());
        map.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(authUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());

                return LoginResponse.builder()
                        .accessToken(jsonNode.get("access_token").asText())
                        .expiresIn(jsonNode.get("expires_in").asLong())
                        .refreshToken(jsonNode.get(REFRESH_TOKEN).asText())
                        .refreshExpiresIn(jsonNode.get("refresh_expires_in").asLong())
                        .tokenType(jsonNode.get("token_type").asText())
                        .build();
            } catch (Exception e) {
                throw new TechnicalException("Failed to parse login response");
            }
        } else {
            throw new TechnicalException("Login failed");
        }
    }

    public LoginResponse loginWithRefreshToken(String refreshToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add(REFRESH_TOKEN, refreshToken);
        map.add("grant_type", REFRESH_TOKEN);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(authUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());

                return LoginResponse.builder()
                        .accessToken(jsonNode.get("access_token").asText())
                        .expiresIn(jsonNode.get("expires_in").asLong())
                        .refreshToken(jsonNode.get(REFRESH_TOKEN).asText())
                        .refreshExpiresIn(jsonNode.get("refresh_expires_in").asLong())
                        .tokenType(jsonNode.get("token_type").asText())
                        .build();
            } catch (Exception e) {
                throw new TechnicalException("Failed to parse refresh token response");
            }
        } else {
            throw new TechnicalException("Refresh token login failed");
        }
    }

    public MessageResponse resetPassword (ResetPasswordRequest resetPasswordRequest) {
        try {
            log.info("Reset password object: {}", resetPasswordRequest);
            if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword())) {
                throw new TechnicalException("Passwords not matched");
            }
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setValue(resetPasswordRequest.getNewPassword());
            credentialRepresentation.setTemporary(false);
            UserRepresentation user = this.getUsersResource().searchByEmail(resetPasswordRequest.getEmail(), true).stream().findFirst().orElseThrow(() -> new RuntimeException("User not found"));
            this.getUserResource(user.getId()).resetPassword(credentialRepresentation);
            return MessageResponse.builder().message("Password reset successfully").build();
        } catch (Exception e) {
            throw new TechnicalException("Error occurred ");
        }
    }

    protected UserRepresentation addUser (UtilisateurRequest utilisateurRequest) {
        log.info("User request: {}", utilisateurRequest);
        UserRepresentation userRepresentation = this.prepareUserRepresentation(utilisateurRequest);
        Response response = this.getUsersResource().create(userRepresentation);
        if (response.getStatus() == HttpStatus.CREATED.value()) {
            List<UserRepresentation> userRepresentationList = this.getUsersResource().searchByUsername(utilisateurRequest.getUsername(), true);
            if (!CollectionUtils.isEmpty(userRepresentationList)) {
                return userRepresentationList.stream().filter(userRepresentation1 -> Objects.equals(false, userRepresentation1.isEmailVerified())).findFirst().orElseThrow(
                        () -> new NotFoundException("User not found")
                );
            }
        }
        return null;
    }

    protected UserRepresentation getUser(String id) {
        return this.getUserResource(id).toRepresentation();
    }

    protected void deleteUser(String id) {
        this.getUserResource(id).remove();
    }

    protected void addRole (RoleRequest roleRequest) {
        RoleRepresentation roleRepresentation = new RoleRepresentation();
        roleRepresentation.setName(roleRequest.getNom().toString());
        this.getRolesResource().create(roleRepresentation);
        if (this.getRole(roleRequest.getNom().toString()) != null) {
            this.getRole(roleRequest.getNom().toString());
        } else {
            throw new NotFoundException("Role not found");
        }
    }

    protected void deleteRole(String roleName) {
        this.getRolesResource().get(roleName).remove();
    }

    protected RoleRepresentation getRole (String roleName) {
        return this.getRolesResource().get(roleName).toRepresentation();
    }

    protected void assignRoleToUser (String userId, String roleName) {
        RoleRepresentation roleRepresentation = this.getRole(roleName);
        UserRepresentation userRepresentation = this.getUser(userId);
        log.info("From assign role {}", userRepresentation.getId());
        this.getUserResource(userRepresentation.getId()).roles().realmLevel().add(List.of(roleRepresentation));
    }

    private UserRepresentation prepareUserRepresentation(UtilisateurRequest utilisateurRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(utilisateurRequest.getPrenom());
        userRepresentation.setLastName(utilisateurRequest.getNom());
        userRepresentation.setEmail(utilisateurRequest.getEmail());
        userRepresentation.setUsername(utilisateurRequest.getUsername());
        userRepresentation.setEnabled(true);
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(utilisateurRequest.getPassword());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        List<CredentialRepresentation> credentialRepresentations = List.of(credentialRepresentation);
        userRepresentation.setCredentials(credentialRepresentations);
        return userRepresentation;
    }

    private UsersResource getUsersResource () {
        return keycloak.realm(realm).users();
    }

    private RolesResource getRolesResource () {
        return keycloak.realm(realm).roles();
    }

    private UserResource getUserResource (String id) {
        UsersResource resource = getUsersResource();
        return resource.get(id);
    }
}
