package com.sistema.rafael.factura.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record FacturaRequest(
        @NotNull Long viajeId,
        @NotNull String numero,
        @NotNull BigDecimal subtotal,
        @NotNull @Positive BigDecimal igv,
        @NotNull @Positive BigDecimal total
) {}
