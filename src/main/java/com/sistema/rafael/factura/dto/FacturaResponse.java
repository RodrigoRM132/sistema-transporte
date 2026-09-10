package com.sistema.rafael.factura.dto;

import com.sistema.rafael.factura.domain.EstadoFactura;
import java.math.BigDecimal;
import java.time.LocalDate;

public record FacturaResponse(
        Long id,
        String numero,
        LocalDate fechaEmision,
        EstadoFactura estado,
        BigDecimal subtotal,
        BigDecimal igv,
        BigDecimal total,
        Long viajeId
) {}
