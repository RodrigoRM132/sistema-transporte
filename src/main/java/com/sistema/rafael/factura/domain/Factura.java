package com.sistema.rafael.factura.domain;

import com.sistema.rafael.viaje.domain.Viaje;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "facturas",
       uniqueConstraints = @UniqueConstraint(name = "uk_factura_numero", columnNames = "numero"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String numero; // Ej: F001-00000012

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoFactura estado;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal igv;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viaje_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_factura_viaje"))
    private Viaje viaje;

    public void aplicarPago(BigDecimal montoPago) {
        if (this.estado == EstadoFactura.ANULADA) {
            throw new IllegalStateException("No se puede pagar una factura anulada");
        }
        if (this.estado == EstadoFactura.PAGADA) {
            throw new IllegalStateException("La factura ya está pagada");
        }
        if (montoPago.compareTo(this.total) < 0) {
            throw new IllegalArgumentException("El monto pagado es menor al total de la factura");
        }
        this.estado = EstadoFactura.PAGADA;
    }
}
