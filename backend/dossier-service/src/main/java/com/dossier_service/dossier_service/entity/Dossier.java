package com.dossier_service.dossier_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String  id;
    private String fkEmailUtilisateur;
    private String  fkIdPatient;
    private LocalDate date;
    private String status;


}
