package com.sistema.rafael.servicio.domain;

import com.sistema.rafael.cliente.domain.Cliente;
import com.sistema.rafael.conductor.domain.Conductor;
import com.sistema.rafael.vehiculo.domain.Vehiculo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "servicios_transporte")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicioTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Cliente
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Relación con Vehiculo
    @ManyToOne(optional = false)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    // Relación con Conductor
    @ManyToOne(optional = false)
    @JoinColumn(name = "conductor_id", nullable = false)
    private Conductor conductor;

    @Column(nullable = false)
    private LocalDate fechaServicio;

    @Column(nullable = false, length = 120)
    private String origen;

    @Column(nullable = false, length = 120)
    private String destino;

    @Column(length = 250)
    private String descripcionCarga;

    @Column(precision = 12, scale = 2)
    private BigDecimal pesoToneladas; // opcional

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monto; // lo que se cobrará por el servicio

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoServicio estado = EstadoServicio.PENDIENTE;

    @Column(nullable = false)
    private boolean activo = true; // baja lógica
}
