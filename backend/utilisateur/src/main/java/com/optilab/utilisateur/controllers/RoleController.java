package com.optilab.utilisateur.controllers;

import com.optilab.utilisateur.dto.request.RoleRequest;
import com.optilab.utilisateur.dto.response.MessageResponse;
import com.optilab.utilisateur.dto.response.RoleResponse;
import com.optilab.utilisateur.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Validated
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok(this.roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(this.roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> addRole (@Valid @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.addRole(roleRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete (@PathVariable Long id) {
        return ResponseEntity.ok(this.roleService.delete(id));
    }
}
