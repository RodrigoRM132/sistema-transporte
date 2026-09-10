package com.sistema.rafael.vehiculo.controller;

import com.sistema.rafael.vehiculo.dto.VehiculoRequest;
import com.sistema.rafael.vehiculo.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService service;

    @PostMapping
    public ResponseEntity<?> crear(
            @RequestBody VehiculoRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody VehiculoRequest request
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