package org.example.exception;

public class LibroStockCompleto extends RuntimeException {
    public LibroStockCompleto(String message) {
        super(message);
    }
}
