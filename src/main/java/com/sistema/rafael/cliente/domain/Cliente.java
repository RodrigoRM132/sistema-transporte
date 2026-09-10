package com.sistema.rafael.cliente.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String razonSocial;

    @Column(nullable = false, length = 20, unique = true)
    private String ruc;

    @Column(length = 120)
    private String correo;

    @Column(length = 20)
    private String telefono;

    @Column(length = 200)
    private String direccion;

    @Builder.Default
    @Column(nullable = false)
    private Boolean activo = true;
}