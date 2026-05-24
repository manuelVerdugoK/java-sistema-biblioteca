package org.example.repository;

import org.example.domain.Libro;

import java.util.ArrayList;
import java.util.Optional;

public class LibroRepository {
    private ArrayList<Libro> libros;

    public LibroRepository() {
        this.libros = new ArrayList<>();
    }

    public void guardarLibro(Libro libro) {
        this.libros.add(libro);
    }

    public Optional<Libro> buscarLibro(int id) {
        return this.libros.stream()
                .filter(u -> u.getIdLibro() == id)
                .findFirst();
    }

    public Optional<Libro> buscarLibro(String titulo){
        return this.libros.stream()
                .filter(u -> u.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public ArrayList<Libro> listarTodos(){
        return new ArrayList<>(libros);
    }
}
