package com.dossier_service.dossier_service.repository;

import com.dossier_service.dossier_service.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamenRepository extends JpaRepository<Examen,Long> {
}
