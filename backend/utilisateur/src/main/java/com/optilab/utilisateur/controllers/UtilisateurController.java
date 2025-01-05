package com.optilab.utilisateur.controllers;

import com.optilab.utilisateur.dto.request.UtilisateurRequest;
import com.optilab.utilisateur.dto.response.MessageResponse;
import com.optilab.utilisateur.dto.response.UtilisateurResponse;
import com.optilab.utilisateur.enums.RoleUtilisateur;
import com.optilab.utilisateur.services.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@Validated
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<UtilisateurResponse>> getAllUtilisateurs() {
        return ResponseEntity.ok(this.utilisateurService.getAllUtilisateurs());
    }

    @GetMapping("/with-role")
    public ResponseEntity<List<UtilisateurResponse>> getUtilisateursByRole(@RequestParam RoleUtilisateur roleUtilisateur) {
        return ResponseEntity.ok(this.utilisateurService.getUtilisateursByRole(roleUtilisateur));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UtilisateurResponse> addUtilisateur(@Valid @RequestBody UtilisateurRequest utilisateurRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.utilisateurService.createUtilisateur(utilisateurRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(this.utilisateurService.delete(id));
    }

    @PostMapping("/{utilisateurId}/assign/{roleName}")
    public ResponseEntity<MessageResponse> assignRoleToUtilisateur (@PathVariable Long utilisateurId, @PathVariable RoleUtilisateur roleName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.utilisateurService.assignRoleToUtilisateur(utilisateurId, roleName));
    }

}
