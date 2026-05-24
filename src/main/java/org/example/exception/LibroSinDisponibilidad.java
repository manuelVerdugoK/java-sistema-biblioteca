package org.example.exception;

public class LibroSinDisponibilidad extends RuntimeException {
    public LibroSinDisponibilidad(String message) {
        super(message);
    }
}
