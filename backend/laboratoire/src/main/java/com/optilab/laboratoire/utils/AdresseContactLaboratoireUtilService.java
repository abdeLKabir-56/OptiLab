package com.optilab.laboratoire.utils;

import com.optilab.laboratoire.DTO.request.AdresseRequest;
import com.optilab.laboratoire.entities.Adresse;
import com.optilab.laboratoire.entities.ContactLaboratoire;
import com.optilab.laboratoire.mappers.AdresseMapper;
import com.optilab.laboratoire.repositories.AdresseRepository;
import com.optilab.laboratoire.repositories.ContactLaboratoireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdresseContactLaboratoireUtilService {

    private final AdresseRepository adresseRepository;
    private final ContactLaboratoireRepository contactLaboratoireRepository;

    public ContactLaboratoire getContactLabById(Long id){
        return this.contactLaboratoireRepository.findById(id).orElseThrow(null);
    }

    public Adresse getAdresseById(Long id){
        return this.adresseRepository.findById(id).orElseThrow(null);
    }

    public ContactLaboratoire getContacts(Long adresseId){
        return this.contactLaboratoireRepository.findByAdresseId(adresseId);
    }

    public Adresse saveAdresse(AdresseRequest adresseRequest){
        return this.adresseRepository.saveAndFlush(AdresseMapper.INSTANCE.toEntity(adresseRequest));
    }

    public void deleteAdresse (Adresse adresse) {
        this.adresseRepository.delete(adresse);
    }

    public ContactLaboratoire saveContactLabo(ContactLaboratoire contactLaboratoire) {
        return this.contactLaboratoireRepository.save(contactLaboratoire);
    }



}