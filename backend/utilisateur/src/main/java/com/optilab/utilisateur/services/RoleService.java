package com.optilab.utilisateur.services;

import com.optilab.utilisateur.dto.request.RoleRequest;
import com.optilab.utilisateur.dto.response.MessageResponse;
import com.optilab.utilisateur.dto.response.RoleResponse;
import com.optilab.utilisateur.entities.Role;
import com.optilab.utilisateur.enums.RoleUtilisateur;
import com.optilab.utilisateur.exception.NotFoundException;
import com.optilab.utilisateur.exception.TechnicalException;
import com.optilab.utilisateur.mappers.RoleMapper;
import com.optilab.utilisateur.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;
    private final KeycloakService keycloakService;

    public List<RoleResponse> getAllRoles() {
        return this.roleRepository.findAll().stream().map(RoleMapper.MAPPER::toResponse).toList();
    }

    public RoleResponse getRoleById(Long id) {
        return this.roleRepository.findById(id).map(RoleMapper.MAPPER::toResponse).orElseThrow(() -> new NotFoundException("Role not found"));
    }

    public RoleResponse addRole(RoleRequest roleRequest) {
        try {
            log.info("Role to add {}", roleRequest);
            Role role = RoleMapper.MAPPER.toEntity(roleRequest);
            this.keycloakService.addRole(roleRequest);
            RoleRepresentation roleRepresentation = this.keycloakService.getRole(roleRequest.getNom().toString());
            role.setKcRoleId(roleRepresentation.getId());
            return RoleMapper.MAPPER.toResponse(this.roleRepository.save(role));
        } catch (Exception e) {
            throw new TechnicalException("Error while adding role");
        }
    }

    public MessageResponse delete (Long id) {
        try {
            Role role = this.roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
            this.keycloakService.deleteRole(role.getNom().toString());
            this.roleRepository.delete(role);
            return MessageResponse.builder().message("Role deleted successfully").build();
        } catch (Exception e) {
            throw new TechnicalException("Error while deleting role");
        }
    }

}
