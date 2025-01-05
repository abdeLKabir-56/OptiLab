package com.optilab.utilisateur.dto.response;

import com.optilab.utilisateur.enums.RoleUtilisateur;

public record RoleResponse (
        Long id,
        RoleUtilisateur nom,
        String kcRoleId
) {
}
