package com.dossier_service.dossier_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "\"analyse\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fkIdLaboratoire;
    private String nom;
    private String description;
    private String status;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = false)
    @JsonBackReference // Prevents recursive serialization back to the parent Dossier
    private Dossier dossier;

    @OneToMany(mappedBy = "analyse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Ensures proper serialization of the relationship
    private List<Epreuve> epreuves;
}

