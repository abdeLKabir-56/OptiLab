package com.optilab.laboratoire.repositories;

import com.optilab.laboratoire.entities.ContactLaboratoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactLaboratoireRepository extends JpaRepository<ContactLaboratoire, Long> {

    @Query(
            "SELECT cl FROM ContactLaboratoire cl WHERE cl.adresse.id = :adresseId"
    )
    ContactLaboratoire findByAdresseId(Long adresseId);
}
