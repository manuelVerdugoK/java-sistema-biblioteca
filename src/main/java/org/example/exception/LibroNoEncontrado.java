package org.example.exception;

public class LibroNoEncontrado extends RuntimeException {
    public LibroNoEncontrado(String message) {
        super(message);
    }
}
