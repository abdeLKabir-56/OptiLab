package com.dossier_service.dossier_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private String nom;

    @ManyToOne
    @JoinColumn(name = "analyse_id", nullable = true)
    @JsonBackReference // Prevents recursion back to the parent Analyse
    private Analyse analyse;

    // One-to-one relationship with Examen
    @OneToOne(mappedBy = "epreuve", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Ensures proper serialization of the relationship
    private Examen examen;
}

