package com.sistema.rafael.factura.dto;

import com.sistema.rafael.factura.domain.EstadoFactura;

import java.time.LocalDate;

public record FacturaFilterRequest(
        String numero,          // "F001" o "00012"
        EstadoFactura estado,   // EMITIDA, PAGADA, ANULADA
        LocalDate desde,        // fechaEmision >= desde
        LocalDate hasta,        // fechaEmision <= hasta

        Long viajeId,
        Long clienteId,
        Long vehiculoId,
        Long conductorId,

        String origen,
        String destino
) {}
