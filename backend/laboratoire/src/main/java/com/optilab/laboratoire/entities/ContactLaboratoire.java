package com.optilab.laboratoire.entities;

import lombok.*;
import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class ContactLaboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "laboratoire_id")
    private Laboratoire laboratoire;

    private Long numTel;

    private Long fax;

    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Adresse adresse;
}
