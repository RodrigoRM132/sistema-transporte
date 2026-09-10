package com.sistema.rafael.cliente.controller;

import com.sistema.rafael.cliente.dto.ClienteRequest;
import com.sistema.rafael.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearCliente(request));
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerCliente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest request
    ) {
        return ResponseEntity.ok(service.actualizarCliente(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}