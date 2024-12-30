package com.optilab.laboratoire.services;

import com.optilab.laboratoire.DTO.request.ContactLaboratoireAdresseRequest;
import com.optilab.laboratoire.DTO.request.ContactLaboratoireRequest;
import com.optilab.laboratoire.DTO.request.LaboratoireRequest;
import com.optilab.laboratoire.DTO.response.DeleteResponse;
import com.optilab.laboratoire.DTO.response.LaboratoireResponse;
import com.optilab.laboratoire.entities.Adresse;
import com.optilab.laboratoire.entities.ContactLaboratoire;
import com.optilab.laboratoire.entities.Laboratoire;
import com.optilab.laboratoire.mappers.AdresseMapper;
import com.optilab.laboratoire.mappers.ContactLaboratoireMapper;
import com.optilab.laboratoire.mappers.LaboratoireMapper;
import com.optilab.laboratoire.repositories.LaboratoireRepository;
import com.optilab.laboratoire.utils.AdresseContactLaboratoireUtilService;
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
    private final AdresseContactLaboratoireUtilService utilService;
    private final ContactLaboratoireService contactLaboratoireService;

    public LaboratoireResponse createLaboratoire(LaboratoireRequest request) {
        Laboratoire laboratoire = LaboratoireMapper.INSTANCE.toEntity(request);
        Laboratoire savedLaboratoire = laboratoireRepository.saveAndFlush(laboratoire);
        ContactLaboratoireAdresseRequest contactLaboratoireAdresseRequest = ContactLaboratoireMapper.INSTANCE.toRequestFromLaboratoireRequest(request);
        Adresse adresse = this.utilService.saveAdresse(AdresseMapper.INSTANCE.toRequest(contactLaboratoireAdresseRequest));
        log.info("Saved adresse : {}", adresse);
        ContactLaboratoireRequest contactLaboratoireRequest = ContactLaboratoireMapper.INSTANCE.toRequest(contactLaboratoireAdresseRequest);
        ContactLaboratoire contactLaboratoire = ContactLaboratoireMapper.INSTANCE.toEntity(contactLaboratoireRequest);
        contactLaboratoire.setLaboratoire(savedLaboratoire);
        contactLaboratoire.setAdresse(adresse);
        ContactLaboratoire savedContactLaboratoire = this.utilService.saveContactLabo(contactLaboratoire);
        log.info("Saved contact labo: {}", savedContactLaboratoire);
        LaboratoireResponse laboratoireResponse = LaboratoireMapper.INSTANCE.toResponse(laboratoire);
        return LaboratoireResponse.builder()
                .contactLaboratoirId(savedContactLaboratoire.getId())
                .nom(laboratoireResponse.nom())
                .nrc(laboratoireResponse.nrc())
                .logo(laboratoireResponse.logo())
                .active(laboratoireResponse.active())
                .dateActivation(laboratoireResponse.dateActivation())
                .adresseId(adresse.getId())
                .id(laboratoireResponse.id())
                .build();
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

    public DeleteResponse deleteLaboratoire(Long id) {
        try {
            log.info("Deleting Laboratoire with ID: {}", id);
            ContactLaboratoire contactLaboratoire = this.contactLaboratoireService.getContactLaboratoireByLaboId(id);
            contactLaboratoireService.deleteContactLaboratoire(contactLaboratoire.getId());
            // Check if the entity exists
            if (!laboratoireRepository.existsById(id)) {
                throw new RuntimeException("Laboratoire not found with ID: " + id);
            }

            // Perform the delete operation
            laboratoireRepository.deleteById(id);
            log.info("Successfully deleted Laboratoire with ID: {}", id);
            return DeleteResponse.builder().message("Laboratoire deleted successfully").build();
        } catch (Exception ex) {
            log.error("Error deleting Laboratoire with ID: {}", id, ex);
            throw new RuntimeException("Failed to delete Laboratoire: " + ex.getMessage(), ex);
        }
    }

}
