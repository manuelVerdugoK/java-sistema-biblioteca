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

    // REGISTRAR UN NUEVO PRESTAMO
    /* ANTIGUO...
    public void registrarPrestamo(int id, int idLibro) {
        // buscamos el libro para ver si existe.
        Libro libroPrestar = libroRepo.buscarLibro(idLibro).orElseThrow(
                () -> new LibroNoEncontrado("EL libro no existe")
        );

        Prestamo nuevoPrestamo = new Prestamo(id, libroPrestar);
        // usamos el repository de libro pasandole el id del libro a través de la referencia de libro
        // en el prestamo, así, obteniendo el ID.
        libroRepo.buscarLibro(
                nuevoPrestamo.getLibro()
                        .getIdLibro()
                // Como el retorno anterior es un Optional, necesitamos validar si arroja un objeto o viene vacío
                // aprovechamos de usar la excepcion custom.
        ).orElseThrow(
                () -> new LibroNoEncontrado("El libro que intenta prestar no existe.")
        );
        // llegando acá, implica que si devolvió objeto
        nuevoPrestamo.getLibro().incrementarPrestados();
        prestamoRepo.guardarPrestamo(nuevoPrestamo);
    }

     */

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
