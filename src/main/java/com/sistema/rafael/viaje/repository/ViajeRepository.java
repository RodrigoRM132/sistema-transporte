package com.sistema.rafael.viaje.repository;

import com.sistema.rafael.viaje.domain.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {
    List<Viaje> findByActivoTrue();
}
