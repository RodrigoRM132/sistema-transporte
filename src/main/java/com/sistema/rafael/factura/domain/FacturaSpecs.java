package com.sistema.rafael.factura.domain;
import java.time.LocalDate;
import com.sistema.rafael.factura.domain.EstadoFactura;
import org.springframework.data.jpa.domain.Specification;

public class FacturaSpecs {

    public static Specification<Factura> numeroLike(String numero) {
        return (root, query, cb) ->
                (numero == null || numero.isBlank()) ? cb.conjunction()
                        : cb.like(cb.lower(root.get("numero")), "%" + numero.toLowerCase() + "%");
    }

    public static Specification<Factura> estadoEq(EstadoFactura estado) {
        return (root, query, cb) -> (estado == null) ? cb.conjunction()
                : cb.equal(root.get("estado"), estado);
    }

    public static Specification<Factura> fechaBetween(LocalDate desde, LocalDate hasta) {
        return (root, query, cb) -> {
            if (desde == null && hasta == null) return cb.conjunction();
            if (desde != null && hasta != null) return cb.between(root.get("fechaEmision"), desde, hasta);
            if (desde != null) return cb.greaterThanOrEqualTo(root.get("fechaEmision"), desde);
            return cb.lessThanOrEqualTo(root.get("fechaEmision"), hasta);
        };
    }

    // join a viaje -> vehiculo -> placa
    public static Specification<Factura> placaEq(String placa) {
        return (root, query, cb) -> {
            if (placa == null || placa.isBlank()) return cb.conjunction();
            var viaje = root.join("viaje");
            var vehiculo = viaje.join("vehiculo");
            return cb.equal(cb.upper(vehiculo.get("placa")), placa.toUpperCase());
        };
    }

    // join a viaje -> cliente -> ruc
    public static Specification<Factura> rucEq(String ruc) {
        return (root, query, cb) -> {
            if (ruc == null || ruc.isBlank()) return cb.conjunction();
            var viaje = root.join("viaje");
            var cliente = viaje.join("cliente");
            return cb.equal(cliente.get("ruc"), ruc);
        };
    }
    public static Specification<Factura> filtrar(
            EstadoFactura estado,
            LocalDate desde,
            LocalDate hasta
    ) {
        return Specification.where(estadoEq(estado))
                .and(fechaBetween(desde, hasta));
    }
}

