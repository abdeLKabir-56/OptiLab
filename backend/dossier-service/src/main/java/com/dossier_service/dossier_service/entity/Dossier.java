package com.dossier_service.dossier_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fkEmailUtilisateur;
    private String fkIdPatient;
    private String nom;
    private String description;

    // Prevent infinite recursion
    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Ensures proper serialization of the relationship
    private List<Analyse> analyses;
}

