package com.optilab.utilisateur.repositories;

import com.optilab.utilisateur.entities.Role;
import com.optilab.utilisateur.enums.RoleUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(RoleUtilisateur name);
    Boolean existsByNom(RoleUtilisateur roleUtilisateur);
}
