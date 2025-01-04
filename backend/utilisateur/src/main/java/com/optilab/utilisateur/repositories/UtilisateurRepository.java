package com.optilab.utilisateur.repositories;

import com.optilab.utilisateur.entities.Utilisateur;
import com.optilab.utilisateur.enums.RoleUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByRoleNom(RoleUtilisateur roleName);
    Boolean existsByEmailOrUsername(String email, String username);
}
