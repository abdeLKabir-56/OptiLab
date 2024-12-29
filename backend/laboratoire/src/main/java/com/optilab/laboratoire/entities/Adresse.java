package com.optilab.laboratoire.entities;

import lombok.*;
import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numVoie;

    private String nomVoie;

    private Long codePostal;

    private String ville;

    private String commune;
}
