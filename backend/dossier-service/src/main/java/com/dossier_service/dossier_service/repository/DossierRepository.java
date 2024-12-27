package com.dossier_service.dossier_service.repository;

import com.dossier_service.dossier_service.entity.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
}
