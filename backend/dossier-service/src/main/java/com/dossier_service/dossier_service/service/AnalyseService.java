package com.dossier_service.dossier_service.service;

import com.dossier_service.dossier_service.entity.Analyse;
import com.dossier_service.dossier_service.exception.ResourceNotFoundException;
import com.dossier_service.dossier_service.repository.AnalyseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyseService {

    private final AnalyseRepository analyseRepository;

    public Analyse createAnalyse(Analyse analyse) {
        return analyseRepository.save(analyse);
    }

    public List<Analyse> getAllAnalyses() {
        return analyseRepository.findAll();
    }

    public Analyse getAnalyseById(Long id) {
        return analyseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Analyse not found with ID: " + id));
    }

    public Analyse updateAnalyse(Long id, Analyse updatedAnalyse) {
        return analyseRepository.findById(id)
                .map(analyse -> {
                    analyse.setNom(updatedAnalyse.getNom());
                    analyse.setDescription(updatedAnalyse.getDescription());
                    analyse.setStatus(updatedAnalyse.getStatus());
                    analyse.setDate(updatedAnalyse.getDate());
                    return analyseRepository.save(analyse);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Analyse not found with ID: " + id));
    }

    public void deleteAnalyse(Long id) {
        if (!analyseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Analyse not found with ID: " + id);
        }
        analyseRepository.deleteById(id);
    }
}
