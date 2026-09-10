package com.sistema.rafael.pago.controller;

import com.sistema.rafael.pago.dto.PagoRequest;
import com.sistema.rafael.pago.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService service;

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody PagoRequest request) {
        return ResponseEntity.ok(service.registrar(request));
    }
}
