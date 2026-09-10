package com.sistema.rafael.viaje.domain;

import com.sistema.rafael.cliente.domain.Cliente;
import com.sistema.rafael.conductor.domain.Conductor;
import com.sistema.rafael.servicio.domain.ServicioTransporte;
import com.sistema.rafael.vehiculo.domain.Vehiculo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "viajes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relaciones
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "servicio_id")
    private ServicioTransporte servicioTransporte;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;

    // Datos del viaje
    @Column(nullable = false, length = 150)
    private String origen;

    @Column(nullable = false, length = 150)
    private String destino;

    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;

    // Carga / costo
    private Double pesoToneladas;        
    private BigDecimal precioAcordado;       // total del servicio (base para facturar)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoViaje estado;

    @Column(nullable = false)
    private boolean activo = true; // baja lógica
}
