package com.optilab.laboratoire.repositories;

import com.optilab.laboratoire.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}
