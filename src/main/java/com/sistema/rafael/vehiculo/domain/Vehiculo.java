package com.sistema.rafael.vehiculo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehiculos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10, unique = true)
    private String placa;  // ABC-123, por ejemplo

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false)
    private int anioFabricacion;

    @Column(nullable = false)
    private Double capacidadCarga; // en toneladas

    @Enumerated(EnumType.STRING)
    private TipoVehiculo tipo; // camión, tráiler, semi, etc.

    @Builder.Default
    @Column(nullable =false)
    private Boolean activo = true; // para baja lógica
}
