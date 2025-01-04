package com.optilab.laboratoire.controllers;

import com.optilab.laboratoire.DTO.request.ContactLaboratoireAdresseRequest;
import com.optilab.laboratoire.DTO.request.ContactLaboratoireRequest;
import com.optilab.laboratoire.DTO.response.ContactLaboratoireResponse;
import com.optilab.laboratoire.DTO.response.DeleteResponse;
import com.optilab.laboratoire.services.ContactLaboratoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts-laboratoire")
@RequiredArgsConstructor
public class ContactLaboratoireController {

    private final ContactLaboratoireService contactLaboratoireService;

    @PostMapping
    public ResponseEntity<ContactLaboratoireResponse> createContact(@RequestBody ContactLaboratoireAdresseRequest contact) {
        ContactLaboratoireResponse savedContact = contactLaboratoireService.createContactLaboratoire(contact);
        return ResponseEntity.status(201).body(savedContact);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactLaboratoireResponse> getContactById(@PathVariable Long id) {
        ContactLaboratoireResponse contact = contactLaboratoireService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @GetMapping
    public ResponseEntity<List<ContactLaboratoireResponse>> getAllContacts() {
        List<ContactLaboratoireResponse> contacts = contactLaboratoireService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactLaboratoireResponse> updateContact(
            @PathVariable Long id, @RequestBody ContactLaboratoireRequest contactRequest) {
        ContactLaboratoireResponse updatedContact = contactLaboratoireService.updateContactLaboratoire(id, contactRequest);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteContact(@PathVariable Long id) {
        return ResponseEntity.ok(this.contactLaboratoireService.deleteContactLaboratoire(id));
    }
}
