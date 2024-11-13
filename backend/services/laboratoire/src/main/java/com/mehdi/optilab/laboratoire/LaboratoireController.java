package com.mehdi.optilab.laboratoire;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/laboratory")
@RequiredArgsConstructor
public class LaboratoireController {

    private final LaboratoireService service;

    @PostMapping
    public ResponseEntity<Long> createLaboratoire(
            @RequestBody @Valid LaboratoireRequest request
    ){
        return ResponseEntity.ok(service.createLaboratoire(request));
    }

    @GetMapping("/{laboratory-id}")
    public ResponseEntity<LaboratoireResponse> findById(
            @PathVariable("laboratory-id") Long laboratoryId
    ){
        return ResponseEntity.ok(service.findById(laboratoryId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LaboratoireResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
