package com.dossier_service.dossier_service.controller;

import com.dossier_service.dossier_service.entity.Dossier;
import com.dossier_service.dossier_service.service.DossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/dossiers")
@RequiredArgsConstructor
public class DossierController {
    private final DossierService dossierService;

    @PostMapping
    public ResponseEntity<Dossier> createDossier(@RequestBody Dossier dossier) {
        Dossier createdDossier = dossierService.createDossier(dossier);
        return new ResponseEntity<>(createdDossier, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dossier>> getAllDossiers() {
        List<Dossier> dossiers = dossierService.getAllDossiers();
        return ResponseEntity.ok(dossiers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dossier> getDossierById(@PathVariable Long id) {
        Dossier dossier = dossierService.getDossierById(id);
        return ResponseEntity.ok(dossier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dossier> updateDossier(@PathVariable Long id, @RequestBody Dossier dossier) {
        Dossier updatedDossier = dossierService.updateDossier(id, dossier);
        return ResponseEntity.ok(updatedDossier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        dossierService.deleteDossier(id);
        return ResponseEntity.noContent().build();
    }
}
