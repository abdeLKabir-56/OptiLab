package com.dossier_service.dossier_service.controller;

import com.dossier_service.dossier_service.entity.Epreuve;
import com.dossier_service.dossier_service.service.EpreuveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/epreuves")
@RequiredArgsConstructor
public class EpreuveController {
    private final EpreuveService epreuveService;

    @PostMapping
    public ResponseEntity<Epreuve> createEpreuve(@RequestBody Epreuve epreuve) {
        Epreuve createdEpreuve = epreuveService.createEpreuve(epreuve);
        return new ResponseEntity<>(createdEpreuve, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Epreuve>> getAllEpreuves() {
        List<Epreuve> epreuves = epreuveService.getAllEpreuves();
        return ResponseEntity.ok(epreuves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epreuve> getEpreuveById(@PathVariable Long id) {
        Epreuve epreuve = epreuveService.getEpreuveById(id);
        return ResponseEntity.ok(epreuve);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Epreuve> updateEpreuve(@PathVariable Long id, @RequestBody Epreuve epreuve) {
        Epreuve updatedEpreuve = epreuveService.updateEpreuve(id, epreuve);
        return ResponseEntity.ok(updatedEpreuve);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpreuve(@PathVariable Long id) {
        epreuveService.deleteEpreuve(id);
        return ResponseEntity.noContent().build();
    }
}
