package com.sistema.rafael.factura.repository;

import com.sistema.rafael.factura.domain.EstadoFactura;
import com.sistema.rafael.factura.domain.Factura;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FacturaSpecification {

    public static Specification<Factura> numeroContains(String numero) {

        return (root, query, cb) ->
                numero == null
                        ? null
                        : cb.like(
                                cb.lower(root.get("numero")),
                                "%" + numero.toLowerCase() + "%"
                        );
    }

    public static Specification<Factura> estadoEquals(EstadoFactura estado) {

        return (root, query, cb) ->
                estado == null
                        ? null
                        : cb.equal(root.get("estado"), estado);
    }

    public static Specification<Factura> fechaBetween(
            LocalDate inicio,
            LocalDate fin
    ) {

        return (root, query, cb) -> {

            if (inicio == null || fin == null) {
                return null;
            }

            return cb.between(
                    root.get("fechaEmision"),
                    inicio,
                    fin
            );
        };
    }
}