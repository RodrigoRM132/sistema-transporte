package com.sistema.rafael.factura.repository;

import com.sistema.rafael.factura.domain.EstadoFactura;
import com.sistema.rafael.factura.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long>,
        JpaSpecificationExecutor<Factura> {

    Optional<Factura> findByNumero(String numero);

    boolean existsByNumero(String numero);

    boolean existsByViaje_Id(Long viajeId);

    List<Factura> findByEstado(EstadoFactura estado);

    List<Factura> findByFechaEmisionBetween(LocalDate inicio, LocalDate fin);
}