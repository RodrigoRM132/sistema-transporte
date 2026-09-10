package com.sistema.rafael.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime fecha;

    private int codigo;

    private String error;

    private String mensaje;

}