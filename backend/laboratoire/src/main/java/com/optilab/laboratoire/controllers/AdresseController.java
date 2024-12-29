package com.optilab.laboratoire.controllers;

import com.optilab.laboratoire.DTO.request.AdresseRequest;
import com.optilab.laboratoire.DTO.response.AdresseResponse;
import com.optilab.laboratoire.DTO.response.DeleteResponse;
import com.optilab.laboratoire.services.AdresseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adresses")
@RequiredArgsConstructor
public class AdresseController {

    private final AdresseService adresseService;

    @PostMapping
    public ResponseEntity<AdresseResponse> createAdresse(@RequestBody AdresseRequest adresseRequest) {
        try {
            AdresseResponse createdAdresse = adresseService.createAdresse(adresseRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAdresse);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresseResponse> getAdresseById(@PathVariable Long id) {
        try {
            AdresseResponse adresseResponse = adresseService.getById(id);
            return ResponseEntity.ok(adresseResponse);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<AdresseResponse>> getAllAdresses() {
        try {
            List<AdresseResponse> adresses = adresseService.getAllAdresses();
            return ResponseEntity.ok(adresses);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdresseResponse> updateAdresse(
            @PathVariable Long id, @RequestBody AdresseRequest adresseRequest) {
        try {
            AdresseResponse updatedAdresse = adresseService.updateAdresse(id, adresseRequest);
            return ResponseEntity.ok(updatedAdresse);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        try {
            boolean exists = adresseService.existsById(id);
            return ResponseEntity.ok(exists);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.adresseService.deleteAddress(id));
    }
}
