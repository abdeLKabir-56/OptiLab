package com.optilab.laboratoire.services;

import com.optilab.laboratoire.DTO.request.LaboratoireRequest;
import com.optilab.laboratoire.DTO.response.LaboratoireResponse;
import com.optilab.laboratoire.entities.Laboratoire;
import com.optilab.laboratoire.mappers.LaboratoireMapper;
import com.optilab.laboratoire.repositories.LaboratoireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LaboratoireService {

    private final LaboratoireRepository laboratoireRepository;

    public LaboratoireResponse createLaboratoire(LaboratoireRequest request) {
        Laboratoire laboratoire = LaboratoireMapper.INSTANCE.toEntity(request);
        laboratoire = laboratoireRepository.save(laboratoire);
        return LaboratoireMapper.INSTANCE.toResponse(laboratoire);
    }

    public LaboratoireResponse getLaboratoireById(Long id) {
        Laboratoire laboratoire = laboratoireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Laboratoire not found with ID: " + id));
        return LaboratoireMapper.INSTANCE.toResponse(laboratoire);
    }

    public List<LaboratoireResponse> getAllLaboratoires() {
        return laboratoireRepository.findAll()
                .stream()
                .map(LaboratoireMapper.INSTANCE::toResponse)
                .collect(Collectors.toList());
    }

    public LaboratoireResponse updateLaboratoire(Long id, LaboratoireRequest dto) {
        try {
            log.info("Updating Laboratoire with ID: {} and data: {}", id, dto);

            // Retrieve the existing entity
            Laboratoire existingLaboratoire = laboratoireRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Laboratoire not found with ID: " + id));

            // Map new fields to the existing entity
            existingLaboratoire.setNom(dto.getNom() != null ? dto.getNom() : existingLaboratoire.getNom());
            existingLaboratoire.setLogo(dto.getLogo() != null ? dto.getLogo() : existingLaboratoire.getLogo());
            existingLaboratoire.setNrc(dto.getNrc() != null ? dto.getNrc() : existingLaboratoire.getNrc());
            existingLaboratoire.setActive(dto.isActive());
            existingLaboratoire.setDateActivation(dto.getDateActivation() != null
                    ? dto.getDateActivation() : existingLaboratoire.getDateActivation());

            // Save the updated entity
            Laboratoire updatedLaboratoire = laboratoireRepository.save(existingLaboratoire);

            return LaboratoireMapper.INSTANCE.toResponse(updatedLaboratoire);
        } catch (Exception ex) {
            log.error("Error updating Laboratoire with ID: {}", id, ex);
            throw new RuntimeException("Failed to update Laboratoire: " + ex.getMessage(), ex);
        }
    }

    public void deleteLaboratoire(Long id) {
        try {
            log.info("Deleting Laboratoire with ID: {}", id);

            // Check if the entity exists
            if (!laboratoireRepository.existsById(id)) {
                throw new RuntimeException("Laboratoire not found with ID: " + id);
            }

            // Perform the delete operation
            laboratoireRepository.deleteById(id);
            log.info("Successfully deleted Laboratoire with ID: {}", id);
        } catch (Exception ex) {
            log.error("Error deleting Laboratoire with ID: {}", id, ex);
            throw new RuntimeException("Failed to delete Laboratoire: " + ex.getMessage(), ex);
        }
    }
}