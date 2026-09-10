package com.sistema.rafael.pago.dto;

import com.sistema.rafael.pago.domain.MetodoPago;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagoRequest {
    private Long facturaId;
    private LocalDate fechaPago;
    private BigDecimal monto;
    private MetodoPago metodoPago;
    private String observacion;
}
