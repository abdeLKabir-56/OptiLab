package com.dossier_service.dossier_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String  id;
    private String  fkIdAnalyse;
    private String nom;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkIdAnalyse", nullable = false,insertable = false, updatable = false)
    private Analyse analyse;
}
