package com.dossier_service.dossier_service.controller;

import com.dossier_service.dossier_service.entity.Analyse;
import com.dossier_service.dossier_service.service.AnalyseService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analyses")
@RequiredArgsConstructor
public class AnalyseController {

    private final AnalyseService analyseService;

    @PostMapping
    public ResponseEntity<Analyse> createAnalyse(@RequestBody Analyse analyse) {
        Analyse createdAnalyse = analyseService.createAnalyse(analyse);
        return new ResponseEntity<>(createdAnalyse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Analyse>> getAllAnalyses() {
        List<Analyse> analyses = analyseService.getAllAnalyses();
        return ResponseEntity.ok(analyses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analyse> getAnalyseById(@PathVariable Long id) {
        Analyse analyse = analyseService.getAnalyseById(id);
        return ResponseEntity.ok(analyse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analyse> updateAnalyse(@PathVariable Long id, @RequestBody Analyse analyse) {
        Analyse updatedAnalyse = analyseService.updateAnalyse(id, analyse);
        return ResponseEntity.ok(updatedAnalyse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyse(@PathVariable Long id) {
        analyseService.deleteAnalyse(id);
        return ResponseEntity.noContent().build();
    }
}
