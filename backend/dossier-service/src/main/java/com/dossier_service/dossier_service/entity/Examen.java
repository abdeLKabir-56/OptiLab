package com.dossier_service.dossier_service.entity;

import com.dossier_service.dossier_service.entity.Dossier;
import com.dossier_service.dossier_service.entity.Epreuve;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "examen")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_num_dossier", nullable = false)
    private Dossier dossier;

    @ManyToOne
    @JoinColumn(name = "fk_id_epreuve", nullable = false)
    private Epreuve epreuve;

    @Column(name = "resultat", nullable = false)
    private String resultat;

    @Column(name = "observations")
    private String observations;
}
