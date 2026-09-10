package com.sistema.rafael.pago.service;

import com.sistema.rafael.factura.domain.EstadoFactura;
import com.sistema.rafael.factura.domain.Factura;
import com.sistema.rafael.factura.repository.FacturaRepository;
import com.sistema.rafael.pago.domain.Pago;
import com.sistema.rafael.pago.dto.PagoRequest;
import com.sistema.rafael.pago.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PagoService {

    private final PagoRepository pagoRepository;
    private final FacturaRepository facturaRepository;

    public Pago registrar(PagoRequest request) {

        Factura factura = facturaRepository.findById(request.getFacturaId())
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        // lógica de negocio en la entidad
        factura.aplicarPago(request.getMonto());

        Pago pago = Pago.builder()
                .factura(factura)
                .fechaPago(request.getFechaPago())
                .monto(request.getMonto())
                .metodoPago(request.getMetodoPago())
                .observacion(request.getObservacion())
                .build();

        return pagoRepository.save(pago);
    }
}

