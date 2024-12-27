package com.dossier_service.dossier_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "analyse", schema = "public")
public class Analyse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String fkIdLaboratoire;
    private String nom;
    private String description;
    @OneToMany(mappedBy = "analyse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Epreuve> epreuves;
}
