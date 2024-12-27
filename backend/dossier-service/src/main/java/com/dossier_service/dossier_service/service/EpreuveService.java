package com.dossier_service.dossier_service.service;

import com.dossier_service.dossier_service.entity.Epreuve;
import com.dossier_service.dossier_service.exception.ResourceNotFoundException;
import com.dossier_service.dossier_service.repository.EpreuveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpreuveService {
    private final EpreuveRepository epreuveRepository;
    @Transactional
    public Epreuve createEpreuve(Epreuve epreuve) {
        return epreuveRepository.save(epreuve);
    }

    public List<Epreuve> getAllEpreuves() {
        return epreuveRepository.findAll();
    }

    public Epreuve getEpreuveById(Long id) {
        return epreuveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Epreuve not found with ID: " + id));
    }

    public Epreuve updateEpreuve(Long id, Epreuve updatedEpreuve) {
        return epreuveRepository.findById(id)
                .map(epreuve -> {
                    epreuve.setNom(updatedEpreuve.getNom());
                    epreuve.setFkIdAnalyse(updatedEpreuve.getFkIdAnalyse());
                    return epreuveRepository.save(epreuve);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Epreuve not found with ID: " + id));
    }

    public void deleteEpreuve(Long id) {
        if (!epreuveRepository.existsById(id)) {
            throw new ResourceNotFoundException("Epreuve not found with ID: " + id);
        }
        epreuveRepository.deleteById(id);
    }
}
