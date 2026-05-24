package org.example.service;

import org.example.domain.Libro;
import org.example.exception.LibroNoEncontrado;
import org.example.repository.LibroRepository;

import java.util.ArrayList;

public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void registrarLibro(int id, String autor, String titulo, String descripcion, int stock){
        Libro nuevoLibro = new Libro(id, autor, titulo, descripcion, stock);
        this.libroRepository.guardarLibro(nuevoLibro);
    }

    public void devolverLibro(int id){
        Libro libro = this.libroRepository.buscarLibro(id).orElseThrow(
                ()-> new LibroNoEncontrado("Libro no encontrado"));
        libro.decrementarPrestados();
    }

    public void prestarLibro(int id){
        Libro libro = this.libroRepository.buscarLibro(id).orElseThrow(
                ()-> new LibroNoEncontrado("Libro no encontrado"));
        libro.incrementarPrestados();
    }

    public String consultarTitulo(String titulo){
        Libro libro = this.libroRepository.buscarLibro(titulo).orElseThrow(
                ()-> new LibroNoEncontrado("Libro no encontrado")
        );
        return libro.toString();
    }

    public ArrayList<Libro> listarLibros(){
        return this.libroRepository.listarTodos();
    }
}
