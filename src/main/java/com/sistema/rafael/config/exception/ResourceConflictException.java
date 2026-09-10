package com.sistema.rafael.config.exception;

public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(String mensaje) {
        super(mensaje);
    }
}