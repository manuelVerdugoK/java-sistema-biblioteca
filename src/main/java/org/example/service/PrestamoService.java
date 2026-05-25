package org.example.service;
import org.example.domain.EstadoPrestamo;
import org.example.domain.Libro;
import org.example.domain.Prestamo;
import org.example.exception.LibroNoEncontrado;
import org.example.exception.PrestamoNoEncontrado;
import org.example.repository.LibroRepository;
import org.example.repository.PrestamoRepository;

public class PrestamoService {
    private final PrestamoRepository prestamoRepo;
    private final LibroRepository libroRepo;


    public PrestamoService(PrestamoRepository prestamoRepo, LibroRepository libroRepo) {
        this.prestamoRepo = prestamoRepo;
        this.libroRepo = libroRepo;
    }

    public void registrarPrestamo(int idPrestamo, int idLibro){
        // validamos que el libro a prestar exisa.
        Libro libroPrestar = libroRepo.buscarLibro(idLibro).orElseThrow(
                ()-> new LibroNoEncontrado("El libro no existe")
        );
        Prestamo prestamo = new Prestamo(idPrestamo, libroPrestar);
        prestamo.getLibro().incrementarPrestados();
        prestamoRepo.guardarPrestamo(prestamo);
    }


    // CONSULTAR UN PRESTAMO
    public String consultarPrestamo(int id){
        return prestamoRepo.buscarPrestamo(id).orElseThrow(
                        ()-> new PrestamoNoEncontrado("No se encontró el prestamo consultado")
                ).toString();
    }
    // DEVOLVER UN LIBRO

    public void devolverLibro(int id){
        Prestamo prestamo = prestamoRepo.buscarPrestamo(id).orElseThrow(
                () -> new PrestamoNoEncontrado("No se encontró el prestamo consultado")
        );
        prestamo.getLibro().decrementarPrestados();
        prestamo.setEstado(EstadoPrestamo.devuelto);
    }
}
