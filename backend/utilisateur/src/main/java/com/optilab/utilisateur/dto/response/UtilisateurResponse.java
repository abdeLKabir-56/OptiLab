package com.optilab.utilisateur.dto.response;

import com.optilab.utilisateur.entities.Role;

import java.time.LocalDate;

public record UtilisateurResponse(
        Long id,
        String prenom,
        String nom,
        String email,
        String username,
        String password,
        String telephone,
        Long laboratoireId,
        Role role,
        String adresse,
        LocalDate dateNaissance,
        String kcUserId
) {
}
