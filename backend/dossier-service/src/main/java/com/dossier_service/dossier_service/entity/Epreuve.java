package com.dossier_service.dossier_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fkIdAnalyse; // Foreign key to Analyse (as Long to match the id type of Analyse)
    private String nom;

    // Many-to-one relationship with Analyse
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkIdAnalyse", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Analyse analyse; // This should map to the 'analyse' in Analyse entity
}
