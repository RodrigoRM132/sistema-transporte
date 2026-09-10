package com.sistema.rafael.factura.controller;

import com.sistema.rafael.factura.domain.EstadoFactura;
import com.sistema.rafael.factura.dto.FacturaFilterRequest;
import com.sistema.rafael.factura.dto.FacturaRequest;
import com.sistema.rafael.factura.service.FacturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService service;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody FacturaRequest request) {
        return ResponseEntity.ok(service.crear(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<?> pagar(@PathVariable Long id) {
        service.marcarPagada(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
        public ResponseEntity<?> buscar(
                @RequestParam(required = false) String numero,
                @RequestParam(required = false) EstadoFactura estado,
                @RequestParam(required = false)
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                LocalDate desde,

                @RequestParam(required = false)
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                LocalDate hasta,

                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size
        ) {

            return ResponseEntity.ok(
                    service.filtrar(
                            numero,
                            estado,
                            desde,
                            hasta,
                            page,
                            size
                    )
            );
        }


}
