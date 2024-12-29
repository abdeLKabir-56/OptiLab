package com.optilab.laboratoire.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Laboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String logo;

    private Long nrc;

    private boolean active;

    private LocalDate dateActivation;

    @OneToMany(mappedBy = "laboratoire", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContactLaboratoire> contacts;
}
