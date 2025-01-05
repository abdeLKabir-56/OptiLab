package com.optilab.laboratoire.services;

import com.optilab.laboratoire.DTO.request.AdresseRequest;
import com.optilab.laboratoire.DTO.request.ContactLaboratoireAdresseRequest;
import com.optilab.laboratoire.DTO.request.ContactLaboratoireRequest;
import com.optilab.laboratoire.DTO.response.ContactLaboratoireResponse;
import com.optilab.laboratoire.DTO.response.DeleteResponse;
import com.optilab.laboratoire.entities.Adresse;
import com.optilab.laboratoire.entities.ContactLaboratoire;
import com.optilab.laboratoire.entities.Laboratoire;
import com.optilab.laboratoire.mappers.AdresseMapper;
import com.optilab.laboratoire.mappers.ContactLaboratoireMapper;
import com.optilab.laboratoire.repositories.ContactLaboratoireRepository;
import com.optilab.laboratoire.repositories.LaboratoireRepository;
import com.optilab.laboratoire.utils.AdresseContactLaboratoireUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactLaboratoireService {

    private final ContactLaboratoireRepository contactLaboratoireRepository;
    private final AdresseContactLaboratoireUtilService utilService;
    private final LaboratoireRepository laboratoireRepository;

    public ContactLaboratoireResponse createContactLaboratoire(ContactLaboratoireAdresseRequest contactLaboratoireAdresseRequest) {
        try {
            log.info("Dto => {}", contactLaboratoireAdresseRequest);
            Laboratoire laboratoire = null;
            AdresseRequest adresseRequest = AdresseMapper.INSTANCE.toRequest(contactLaboratoireAdresseRequest);
            Adresse adresse = this.utilService.saveAdresse(adresseRequest);
            if (contactLaboratoireAdresseRequest.getLaboratoireId() != null)
                laboratoire = laboratoireRepository.findById(contactLaboratoireAdresseRequest.getLaboratoireId())
                        .orElseThrow(() -> new IllegalArgumentException("Laboratoire not found with ID: " + contactLaboratoireAdresseRequest.getLaboratoireId()));
            ContactLaboratoireRequest contactLaboratoireRequest = ContactLaboratoireMapper.INSTANCE.toRequest(contactLaboratoireAdresseRequest);
            ContactLaboratoire contactLaboratoire = ContactLaboratoireMapper.INSTANCE.toEntity(contactLaboratoireRequest);
            contactLaboratoire.setLaboratoire(laboratoire);
            contactLaboratoire.setAdresse(adresse);

            return ContactLaboratoireMapper.INSTANCE.toResponse(contactLaboratoireRepository.save(contactLaboratoire));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    public ContactLaboratoireResponse getContactById(Long id) {
        log.info("Fetching ContactLaboratoire by ID: {}", id);

        ContactLaboratoire contactLaboratoire = contactLaboratoireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ContactLaboratoire not found with ID: " + id));

        return ContactLaboratoireMapper.INSTANCE.toResponse(contactLaboratoire);
    }

    public List<ContactLaboratoireResponse> getAllContacts() {
        log.info("Fetching all ContactLaboratoires");

        List<ContactLaboratoire> contacts = contactLaboratoireRepository.findAll();
        return contacts.stream()
                .map(ContactLaboratoireMapper.INSTANCE::toResponse)
                .collect(Collectors.toList());
    }

    public ContactLaboratoireResponse updateContactLaboratoire(Long id, ContactLaboratoireRequest dto) {
        try {
            log.info("Updating ContactLaboratoire with ID: {} and data: {}", id, dto);

            // Retrieve the existing entity
            ContactLaboratoire existingContact = contactLaboratoireRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ContactLaboratoire not found with ID: " + id));

            // Update related Laboratoire entity if provided
            if (dto.getLaboratoireId() != null) {
                Laboratoire laboratoire = laboratoireRepository.findById(dto.getLaboratoireId())
                        .orElseThrow(() -> new RuntimeException("Laboratoire not found with ID: " + dto.getLaboratoireId()));
                existingContact.setLaboratoire(laboratoire);
            }

            // Update Adresse entity if needed
            if (dto.getAdresseId() != null) {
                Adresse adresse = utilService.getAdresseById(dto.getAdresseId());
                existingContact.setAdresse(adresse);
            }

            // Map the remaining fields
            existingContact.setNumTel(dto.getNumTel() != null ? dto.getNumTel() : existingContact.getNumTel());
            existingContact.setFax(dto.getFax() != null ? dto.getFax() : existingContact.getFax());
            existingContact.setEmail(dto.getEmail() != null ? dto.getEmail() : existingContact.getEmail());

            // Save the updated entity
            ContactLaboratoire updatedContact = contactLaboratoireRepository.save(existingContact);

            return ContactLaboratoireMapper.INSTANCE.toResponse(updatedContact);
        } catch (Exception ex) {
            log.error("Error updating ContactLaboratoire with ID: {}", id, ex);
            throw new RuntimeException("Failed to update ContactLaboratoire: " + ex.getMessage(), ex);
        }
    }

    public ContactLaboratoire getContactLabById(Long id){
        return this.contactLaboratoireRepository.findById(id).orElseThrow(null);
    }

    public DeleteResponse deleteContactLaboratoire(Long id){

        log.info("id => {}", id);
        ContactLaboratoire contactLaboratoire = this.contactLaboratoireRepository.findById(id).orElseThrow(() -> new RuntimeException("Error while deleting"));
        if (contactLaboratoire != null) {
            Adresse adresse = contactLaboratoire.getAdresse();
            this.contactLaboratoireRepository.delete(contactLaboratoire);
            this.utilService.deleteAdresse(adresse);
        }
        return DeleteResponse.builder()
                .message("Contact Laboratory deleted Successfully")
                .build();
    }

    public ContactLaboratoire getContactLaboratoireByLaboId(Long laboId) {
        return this.contactLaboratoireRepository.findByLaboratoireId(laboId).orElseThrow(() -> new RuntimeException("Contact labo not found"));
    }
}