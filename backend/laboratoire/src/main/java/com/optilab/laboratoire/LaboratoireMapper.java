package com.optilab.laboratoire;

import org.springframework.stereotype.Service;

@Service
public class LaboratoireMapper {
    public Laboratoire toLaboratory(LaboratoireRequest request) {
        return Laboratoire.builder()
                .id(request.id())
                .nom(request.nom())
                .logo(request.logo())
                .nrc(request.nrc())
                .isActive(request.isActive())
                .dateActivation(request.dateActivation())
                .build();
    }

    public LaboratoireResponse toLaboratoryResponse(Laboratoire laboratoire) {
        return new LaboratoireResponse(
                laboratoire.getId(),
                laboratoire.getNom(),
                laboratoire.getLogo(),
                laboratoire.getNrc(),
                laboratoire.isActive(),
                laboratoire.getDateActivation()
        );
    }
}