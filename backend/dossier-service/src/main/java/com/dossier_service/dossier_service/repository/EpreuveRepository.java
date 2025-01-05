package com.dossier_service.dossier_service.repository;

import com.dossier_service.dossier_service.entity.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
}
