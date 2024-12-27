package com.dossier_service.dossier_service.controller;

import com.dossier_service.dossier_service.entity.Analyse;
import com.dossier_service.dossier_service.service.AnalyseService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/analyses")
@RequiredArgsConstructor()
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
    public ResponseEntity<Analyse> getAnalyseById(@PathVariable String  id) {
        Analyse analyse = analyseService.getAnalyseById(id);
        return ResponseEntity.ok(analyse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analyse> updateAnalyse(@PathVariable String  id, @RequestBody Analyse analyse) {
        Analyse updatedAnalyse = analyseService.updateAnalyse(id, analyse);
        return ResponseEntity.ok(updatedAnalyse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalyse(@PathVariable String  id) {
        analyseService.deleteAnalyse(id);
        return ResponseEntity.noContent().build();
    }
}
