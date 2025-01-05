package com.optilab.utilisateur.dto.request;

import com.optilab.utilisateur.entities.Role;
import com.optilab.utilisateur.enums.RoleUtilisateur;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class UtilisateurRequest {

    @NotBlank(message = "Prenom est obligatoire")
    private String prenom;
    @NotBlank(message = "Nom est obligatoire")
    private String nom;
    @NotBlank(message = "Email est obligatoire")
    private String email;
    @NotBlank(message = "Username est obligatoire")
    private String username;
    @NotBlank(message = "Password est obligatoire")
    private String password;
    @NotBlank(message = "Telephone est obligatoire")
    private String telephone;
    private Long laboratoireId;
    private RoleUtilisateur roleName;
    private String adresse;
    private LocalDate dateNaissance;

}
