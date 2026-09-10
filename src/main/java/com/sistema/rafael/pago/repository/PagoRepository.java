package com.sistema.rafael.pago.repository;

import com.sistema.rafael.pago.domain.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("select coalesce(sum(p.monto), 0) from Pago p where p.factura.id = :facturaId")
    BigDecimal totalPagado(Long facturaId);
}
