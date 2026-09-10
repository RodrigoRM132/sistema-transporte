package com.sistema.rafael.conductor.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conductores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12, unique = true)
    private String dni;

    @Column(nullable = false, length = 120)
    private String nombres;

    @Column(nullable = false, length = 120)
    private String apellidos;

    @Column(length = 20)
    private String telefono;

    @Column(length = 120)
    private String correo;

    @Column(nullable = false, length = 20, unique = true)
    private String licencia; // brevete (AIIIC, etc.)

    @Builder.Default
    @Column(nullable = false)
    private Boolean activo = true; // baja lógica
}
