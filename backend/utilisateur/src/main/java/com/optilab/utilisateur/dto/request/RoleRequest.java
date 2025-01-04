package com.optilab.utilisateur.dto.request;

import com.optilab.utilisateur.enums.RoleUtilisateur;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class RoleRequest {

    private RoleUtilisateur nom;

}
