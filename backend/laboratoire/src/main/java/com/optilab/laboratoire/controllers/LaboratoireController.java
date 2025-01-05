package com.optilab.laboratoire.controllers;

import com.optilab.laboratoire.DTO.request.LaboratoireRequest;
import com.optilab.laboratoire.DTO.response.LaboratoireResponse;
import com.optilab.laboratoire.services.LaboratoireService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/laboratoires")
@RequiredArgsConstructor
public class LaboratoireController {

    private final LaboratoireService laboratoireService;

    @PostMapping
    public ResponseEntity<LaboratoireResponse> createLaboratoire(@RequestBody LaboratoireRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratoireService.createLaboratoire(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN')"
    )
    public ResponseEntity<LaboratoireResponse> getLaboratoireById(@PathVariable Long id) {
        return ResponseEntity.ok(laboratoireService.getLaboratoireById(id));
    }

    @GetMapping
    public ResponseEntity<List<LaboratoireResponse>> getAllLaboratoires() {
        return ResponseEntity.ok(laboratoireService.getAllLaboratoires());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratoireResponse> updateLaboratoire(@PathVariable Long id,
                                                                 @RequestBody LaboratoireRequest request) {
        return ResponseEntity.ok(laboratoireService.updateLaboratoire(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoire(@PathVariable Long id) {
        laboratoireService.deleteLaboratoire(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/is-exist")
    public Boolean isLaboratoireExist(@PathVariable Long id) {
        return this.laboratoireService.isLaboExist(id);
    }
}