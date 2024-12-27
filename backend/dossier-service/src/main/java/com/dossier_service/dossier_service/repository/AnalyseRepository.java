package com.dossier_service.dossier_service.repository;

import com.dossier_service.dossier_service.entity.Analyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnalyseRepository extends JpaRepository<Analyse, Long> {
}
