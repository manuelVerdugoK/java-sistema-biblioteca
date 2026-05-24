package org.example.repository;

import org.example.domain.Prestamo;

import java.util.ArrayList;
import java.util.Optional;

public class PrestamoRepository {
    private ArrayList<Prestamo> prestamos;

    public PrestamoRepository() {this.prestamos = new ArrayList<>();}

    public void guardarPrestamo(Prestamo prestamo){
        this.prestamos.add(prestamo);
    }

    public Optional<Prestamo> buscarPrestamo(int id){
        return this.prestamos.stream().
                filter(u-> u.getId() == id)
                .findFirst();
    }

    public ArrayList<Prestamo> listarTodos(){
        return new ArrayList<>(prestamos);
    }
}
