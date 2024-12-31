package com.dossier_service.dossier_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resultat;
    private String observations;

    @OneToOne
    @JoinColumn(name = "epreuve_id", nullable = false)
    @JsonBackReference // Prevents recursion back to the parent Epreuve
    private Epreuve epreuve;
}
