package com.optilab.laboratoire;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/laboratoires")
@AllArgsConstructor
public class LaboratoireController {

    private final LaboratoireService laboratoireService;

    // Create a new Laboratoire
    @PostMapping
    public ResponseEntity<LaboratoireResponse> createLaboratoire(@Valid @RequestBody LaboratoireRequest laboratoireRequest) {
        LaboratoireResponse response = laboratoireService.createLaboratoire(laboratoireRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all Laboratoires
    @GetMapping
    public ResponseEntity<List<LaboratoireResponse>> getAllLaboratoires() {
        List<LaboratoireResponse> laboratoires = laboratoireService.getAllLaboratoires();
        return ResponseEntity.ok(laboratoires);
    }

    // Get a single Laboratoire by ID
    @GetMapping("/{id}")
    public ResponseEntity<LaboratoireResponse> getLaboratoireById(@PathVariable Long id) {
        LaboratoireResponse laboratoire = laboratoireService.getLaboratoireById(id);
        return ResponseEntity.ok(laboratoire);
    }

    // Update an existing Laboratoire by ID
    @PutMapping("/{id}")
    public ResponseEntity<LaboratoireResponse> updateLaboratoire(@PathVariable Long id, @Valid @RequestBody LaboratoireRequest laboratoireRequest) {
        LaboratoireResponse updatedLaboratoire = laboratoireService.updateLaboratoire(id, laboratoireRequest);
        return ResponseEntity.ok(updatedLaboratoire);
    }

    // Delete a Laboratoire by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoire(@PathVariable Long id) {
        laboratoireService.deleteLaboratoire(id);
        return ResponseEntity.noContent().build();
    }
}
