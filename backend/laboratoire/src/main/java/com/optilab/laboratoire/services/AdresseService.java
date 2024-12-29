package com.optilab.laboratoire.services;

import com.optilab.laboratoire.DTO.request.AdresseRequest;
import com.optilab.laboratoire.DTO.response.AdresseResponse;
import com.optilab.laboratoire.DTO.response.DeleteResponse;
import com.optilab.laboratoire.entities.Adresse;
import com.optilab.laboratoire.entities.ContactLaboratoire;
import com.optilab.laboratoire.mappers.AdresseMapper;
import com.optilab.laboratoire.mappers.ContactLaboratoireMapper;
import com.optilab.laboratoire.repositories.AdresseRepository;
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
public class AdresseService {

    private final AdresseRepository adresseRepository;
    private final AdresseContactLaboratoireUtilService utilService;

    public AdresseResponse getById(Long id) {
        try {
            log.info("Fetching Adresse by ID: {}", id);
            Adresse adresse = adresseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Adresse not found with ID: " + id));
            return AdresseMapper.INSTANCE.toResponse(adresse);
        } catch (Exception ex) {
            log.error("Error fetching Adresse by ID: {}", id, ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public AdresseResponse createAdresse(AdresseRequest dto) {
        try {
            log.info("Creating Adresse with data: {}", dto);
            Adresse adresse = AdresseMapper.INSTANCE.toEntity(dto);
            ContactLaboratoire contactLaboratoire = new ContactLaboratoire();
            if(dto.getContactLaboratoireId() != null)
                contactLaboratoire = utilService.getContactLabById(dto.getContactLaboratoireId());
            Adresse savedAdresse = adresseRepository.save(adresse);
            return AdresseMapper.INSTANCE.toResponse(savedAdresse);
        } catch (Exception ex) {
            log.error("Error creating Adresse with data: {}", dto, ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public AdresseResponse updateAdresse(Long id, AdresseRequest dto) {
        try {
            log.info("Updating Adresse with ID: {} and data: {}", id, dto);
            Adresse existingAdresse = adresseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Adresse not found with ID: " + id));

            this.prepareUpdate(existingAdresse, dto);

            Adresse savedAdresse = adresseRepository.save(existingAdresse);
            return AdresseMapper.INSTANCE.toResponse(savedAdresse);
        } catch (Exception ex) {
            log.error("Error updating Adresse with ID: {}", id, ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private void prepareUpdate(Adresse adresse, AdresseRequest request){
        if (request.getCodePostal() != null)
            adresse.setCodePostal(request.getCodePostal());

        if (request.getNomVoie() != null)
            adresse.setNomVoie(request.getNomVoie());

        if (request.getNumVoie() != null)
            adresse.setNumVoie(request.getNumVoie());

        if (request.getVille() != null)
            adresse.setVille(request.getVille());

        if (request.getCommune() != null)
            adresse.setCommune(request.getCommune());
    }

    public List<AdresseResponse> getAllAdresses() {
        try {
            log.info("Fetching all Adresses");
            List<Adresse> adresses = adresseRepository.findAll();
            return adresses.stream()
                    .map(AdresseMapper.INSTANCE::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error fetching all Adresses", ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public boolean existsById(Long id) {
        try {
            log.info("Checking if Adresse exists with ID: {}", id);
            return adresseRepository.existsById(id);
        } catch (Exception ex) {
            log.error("Error checking existence of Adresse with ID: {}", id, ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public DeleteResponse deleteAddress(Long id){

        try {
            Adresse adresse = this.utilService.getAdresseById(id);
            if (adresse != null){
                throw new RuntimeException("This address Cannot be deleted because it has a laboratory contact");
            } else if (adresse != null) {
                this.adresseRepository.delete(adresse);
            }
            return DeleteResponse.builder()
                    .message("Address deleted Successfully")
                    .build();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
