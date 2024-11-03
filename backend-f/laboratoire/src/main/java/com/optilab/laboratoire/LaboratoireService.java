package com.optilab.laboratoire;

import com.optilab.laboratoire.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LaboratoireService {

    private final LaboratoireRepository laboratoireRepository;
    private final LaboratoireMapper laboratoireMapper;

    public LaboratoireResponse createLaboratoire(LaboratoireRequest laboratoireRequest) {
        Laboratoire laboratoire = laboratoireMapper.toLaboratory(laboratoireRequest);
        Laboratoire savedLaboratoire = laboratoireRepository.save(laboratoire);
        return laboratoireMapper.toLaboratoryResponse(savedLaboratoire);
    }

    public List<LaboratoireResponse> getAllLaboratoires() {
        return laboratoireRepository.findAll().stream()
                .map(laboratoireMapper::toLaboratoryResponse)
                .collect(Collectors.toList());
    }

    public LaboratoireResponse getLaboratoireById(Long id) {
        return laboratoireRepository.findById(id)
                .map(laboratoireMapper::toLaboratoryResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratoire not found with id: " + id));
    }

    public LaboratoireResponse updateLaboratoire(Long id, LaboratoireRequest laboratoireRequest) {
        Laboratoire existingLaboratoire = laboratoireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratoire not found with id: " + id));

        existingLaboratoire.setNom(laboratoireRequest.nom());
        existingLaboratoire.setLogo(laboratoireRequest.logo());
        existingLaboratoire.setNrc(laboratoireRequest.nrc());
        existingLaboratoire.setActive(laboratoireRequest.isActive());
        existingLaboratoire.setDateActivation(laboratoireRequest.dateActivation());

        Laboratoire updatedLaboratoire = laboratoireRepository.save(existingLaboratoire);
        return laboratoireMapper.toLaboratoryResponse(updatedLaboratoire);
    }

    public void deleteLaboratoire(Long id) {
        if (!laboratoireRepository.existsById(id)) {
            throw new ResourceNotFoundException("Laboratoire not found with id: " + id);
        }
        laboratoireRepository.deleteById(id);
    }
}
