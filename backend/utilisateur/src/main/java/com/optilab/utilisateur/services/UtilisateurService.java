package com.optilab.utilisateur.services;

import com.optilab.utilisateur.client.LaboratoireClient;
import com.optilab.utilisateur.dto.request.RoleRequest;
import com.optilab.utilisateur.dto.request.UtilisateurRequest;
import com.optilab.utilisateur.dto.response.MessageResponse;
import com.optilab.utilisateur.dto.response.UtilisateurResponse;
import com.optilab.utilisateur.entities.Role;
import com.optilab.utilisateur.entities.Utilisateur;
import com.optilab.utilisateur.enums.RoleUtilisateur;
import com.optilab.utilisateur.exception.NotFoundException;
import com.optilab.utilisateur.exception.TechnicalException;
import com.optilab.utilisateur.mappers.UtilisateurMapper;
import com.optilab.utilisateur.repositories.RoleRepository;
import com.optilab.utilisateur.repositories.UtilisateurRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilisateurService {

    private final KeycloakService keycloakService;
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final LaboratoireClient laboratoireClient;
    private final MailSenderService senderService;
    private final RoleService roleService;

    public List<UtilisateurResponse> getAllUtilisateurs() {
        return this.utilisateurRepository.findAll().stream().map(UtilisateurMapper.MAPPER::toResponse).toList();
    }

    public List<UtilisateurResponse> getUtilisateursByRole (RoleUtilisateur role) {
        return this.utilisateurRepository.findByRoleNom(role).stream().map(UtilisateurMapper.MAPPER::toResponse).toList();
    }

    @Transactional
    public UtilisateurResponse createUtilisateur(UtilisateurRequest utilisateurRequest) {
        try {
            if (utilisateurRequest.getLaboratoireId() != null && Boolean.FALSE.equals(this.laboratoireClient.isLaboExist(utilisateurRequest.getLaboratoireId()))) {
                throw new NotFoundException("Laboratoire not found");
            }
            Utilisateur utilisateur = UtilisateurMapper.MAPPER.toEntity(utilisateurRequest);
            Role role = null;
            if (utilisateurRequest.getRoleName() != null){
                role = this.roleRepository.findByNom(utilisateurRequest.getRoleName()).orElseThrow(() -> new NotFoundException("Role not found"));
                utilisateur.setRole(role);
            }
            UserRepresentation userRepresentation = this.keycloakService.addUser(utilisateurRequest);
            log.info("User created in keycloak: {}", userRepresentation.getId());
            utilisateur.setKcUserId(userRepresentation.getId());
            if (role != null) {
                this.keycloakService.assignRoleToUser(userRepresentation.getId(), role.getNom().toString());
            }
            Utilisateur savedUtilisateur = this.utilisateurRepository.save(utilisateur);
            this.senderService.sendCredentialsEmail(savedUtilisateur.getEmail(), savedUtilisateur.getPassword());
            return UtilisateurMapper.MAPPER.toResponse(savedUtilisateur);
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage());
        }
    }

    public MessageResponse delete (Long id) {
        try {
            Utilisateur utilisateur = this.utilisateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
            this.keycloakService.deleteUser(utilisateur.getKcUserId());
            this.utilisateurRepository.delete(utilisateur);
            return MessageResponse.builder().message("Utilisateur supprime avec succes").build();
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage());
        }
    }

    public MessageResponse assignRoleToUtilisateur (Long userId, RoleUtilisateur roleName) {
        try {
            Utilisateur utilisateur = this.utilisateurRepository.findById(userId).orElseThrow(() -> new NotFoundException("Utilisateur not found"));
            Role role = this.roleRepository.findByNom(roleName).orElseThrow(() -> new NotFoundException("Role not found"));
            this.keycloakService.assignRoleToUser(utilisateur.getKcUserId(), role.getNom().toString());
            return MessageResponse.builder().message("Role assigned to user").build();
        } catch (Exception e) {
            throw new TechnicalException(e.getMessage());
        }
    }

    @PostConstruct
    @Transactional
    public void add () {
        for (RoleUtilisateur roleName : RoleUtilisateur.values()) {
            if (Boolean.FALSE.equals(this.roleRepository.existsByNom(roleName))) {
                RoleRequest roleRequest = RoleRequest.builder().nom(roleName).build();
                this.roleService.addRole(roleRequest);
            } else {
                log.info("Role already exists: {}", roleName);
            }
        }
        UtilisateurRequest utilisateurRequest = UtilisateurRequest.builder()
                .nom("Admin")
                .prenom("Mehdi")
                .adresse("Khouribga")
                .dateNaissance(LocalDate.of(2002, 12, 15))
                .email("elhilali.elmehdi.edu@gmail.com")
                .username("mehdi_hilali")
                .password("123456")
                .telephone("+212 6 00 11 22 33")
                .roleName(RoleUtilisateur.ADMIN)
                .build();
        if (Boolean.FALSE.equals(this.utilisateurRepository.existsByEmailOrUsername(utilisateurRequest.getEmail(), utilisateurRequest.getUsername()))) {
            UtilisateurResponse utilisateurResponse = this.createUtilisateur(utilisateurRequest);
            log.info("Created user: {}", utilisateurResponse);
        } else {
            log.info("User already exists: {}", utilisateurRequest.getEmail());
        }
    }

}
