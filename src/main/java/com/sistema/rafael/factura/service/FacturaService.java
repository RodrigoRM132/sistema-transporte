package com.sistema.rafael.factura.service;

import com.sistema.rafael.factura.domain.EstadoFactura;
import com.sistema.rafael.factura.domain.Factura;
import com.sistema.rafael.factura.domain.FacturaSpecs;
import com.sistema.rafael.factura.dto.FacturaRequest;
import com.sistema.rafael.factura.dto.FacturaResponse;
import com.sistema.rafael.factura.repository.FacturaRepository;
import com.sistema.rafael.factura.repository.FacturaSpecification;
import com.sistema.rafael.viaje.domain.Viaje;
import com.sistema.rafael.viaje.repository.ViajeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ViajeRepository viajeRepository;

    public FacturaResponse crear(FacturaRequest req) {

        if (facturaRepository.existsByNumero(req.numero()))
            throw new RuntimeException("El número de factura ya existe: " + req.numero());

        if (facturaRepository.existsByViaje_Id(req.viajeId()))
            throw new RuntimeException("Ese viaje ya tiene factura.");

        Viaje viaje = viajeRepository.findById(req.viajeId())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado: " + req.viajeId()));

        var factura = com.sistema.rafael.factura.domain.Factura.builder()
                .numero(req.numero())
                .fechaEmision(LocalDate.now())
                .estado(EstadoFactura.EMITIDA)
                .subtotal(req.subtotal())
                .igv(req.igv())
                .total(req.total())
                .viaje(viaje)
                .build();

        var saved = facturaRepository.save(factura);

        return new FacturaResponse(
                saved.getId(),
                saved.getNumero(),
                saved.getFechaEmision(),
                saved.getEstado(),
                saved.getSubtotal(),
                saved.getIgv(),
                saved.getTotal(),
                saved.getViaje().getId()
        );
    }

    public FacturaResponse obtener(Long id) {
        var f = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada: " + id));

        return new FacturaResponse(
                f.getId(), f.getNumero(), f.getFechaEmision(), f.getEstado(),
                f.getSubtotal(), f.getIgv(), f.getTotal(), f.getViaje().getId()
        );
    }

    public void marcarPagada(Long id) {
        var f = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada: " + id));
        f.setEstado(EstadoFactura.PAGADA);
        facturaRepository.save(f);
    }

    public List<FacturaResponse> listarConFiltros(EstadoFactura estado, LocalDate desde, LocalDate hasta) {
        return facturaRepository.findAll(FacturaSpecs.filtrar(estado, desde, hasta))
                .stream().map(f -> new FacturaResponse(
                        f.getId(), f.getNumero(), f.getFechaEmision(), f.getEstado(),
                        f.getSubtotal(), f.getIgv(), f.getTotal(), f.getViaje().getId()
                ))
                .toList();
    }

        public Page<FacturaResponse> filtrar(
                String numero,
                EstadoFactura estado,
                LocalDate fechaInicio,
                LocalDate fechaFin,
                int page,
                int size
        ) {

        Specification<Factura> spec = Specification
                .where(FacturaSpecification.numeroContains(numero))
                .and(FacturaSpecification.estadoEquals(estado))
                .and(FacturaSpecification.fechaBetween(fechaInicio, fechaFin));

        Pageable pageable = PageRequest.of(page, size);

        return facturaRepository.findAll(spec, pageable)
                .map(this::toResponse);
        }

    private FacturaResponse toResponse(Factura factura) {
        return new FacturaResponse(
                factura.getId(),
                factura.getNumero(),
                factura.getFechaEmision(),
                factura.getEstado(),
                factura.getSubtotal(),
                factura.getIgv(),
                factura.getTotal(),
                factura.getViaje().getId()
        );
    }

}
