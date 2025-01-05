package com.dossier_service.dossier_service.service;

import com.dossier_service.dossier_service.entity.Dossier;
import com.dossier_service.dossier_service.exception.ResourceNotFoundException;
import com.dossier_service.dossier_service.repository.DossierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DossierService {

    private final DossierRepository dossierRepository;

    public Dossier createDossier(Dossier dossier) {
        return dossierRepository.save(dossier);
    }

    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    public Dossier getDossierById(Long id) {
        return dossierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier not found with ID: " + id));
    }

    public Dossier updateDossier(Long id, Dossier updatedDossier) {
        return dossierRepository.findById(id)
                .map(existingDossier -> {
                    existingDossier.setNom(updatedDossier.getNom());
                    existingDossier.setDescription(updatedDossier.getDescription());
                    return dossierRepository.save(existingDossier);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Dossier not found with ID: " + id));
    }

    public void deleteDossier(Long id) {
        if (!dossierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dossier not found with ID: " + id);
        }
        dossierRepository.deleteById(id);
    }
}
