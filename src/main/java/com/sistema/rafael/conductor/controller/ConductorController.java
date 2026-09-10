package com.sistema.rafael.conductor.controller;

import com.sistema.rafael.conductor.dto.ConductorRequest;
import com.sistema.rafael.conductor.service.ConductorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conductores")
@RequiredArgsConstructor
public class ConductorController {

    private final ConductorService service;

    @PostMapping
    public ResponseEntity<?> crear(
            @Valid @RequestBody ConductorRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ConductorRequest request
    ) {
        return ResponseEntity.ok(
                service.actualizar(id, request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                service.obtener(id)
        );
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(
                service.listar()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}