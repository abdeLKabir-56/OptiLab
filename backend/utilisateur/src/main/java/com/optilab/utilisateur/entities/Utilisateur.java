package com.optilab.utilisateur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private String username;
    private String password;
    private String telephone;
    private Long laboratoireId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    private String adresse;
    private LocalDate dateNaissance;
    private String kcUserId;
}
