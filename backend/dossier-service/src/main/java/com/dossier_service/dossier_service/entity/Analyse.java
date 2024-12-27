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
@Table(name = "analyse", schema = "public")
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fkIdLaboratoire;
    private String nom;
    private String description;

    // One-to-many relationship with Epreuve
    @OneToMany(mappedBy = "analyse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Epreuve> epreuves; // mappedBy should refer to 'analyse' in the Epreuve entity
}
