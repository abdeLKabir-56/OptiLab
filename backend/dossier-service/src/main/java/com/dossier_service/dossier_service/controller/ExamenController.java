package com.dossier_service.dossier_service.controller;

import com.dossier_service.dossier_service.entity.Examen;
import com.dossier_service.dossier_service.service.ExamenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/examens")
@RequiredArgsConstructor
public class ExamenController {
    private final ExamenService examenService;
    @PostMapping
    public ResponseEntity<Examen> createExamen(@RequestBody Examen examen) {
        Examen examenCreated = examenService.createExamen(examen);
        return ResponseEntity.ok(examenCreated);
    }
    @GetMapping
    public ResponseEntity<List<Examen>> getAllExamens() {
        List<Examen> examenList = examenService.getAllExamens();
        return ResponseEntity.ok(examenList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Examen> getExamenById(@PathVariable Long id) {
        Examen examen = examenService.findExamenById(id);
        return ResponseEntity.ok(examen);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Examen> updateExamen(@PathVariable Long id, @RequestBody Examen examen) {
        Examen examenUpdated = examenService.updateExamen(id, examen);
        return ResponseEntity.ok(examenUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Examen> deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
        return ResponseEntity.noContent().build();
    }
}
