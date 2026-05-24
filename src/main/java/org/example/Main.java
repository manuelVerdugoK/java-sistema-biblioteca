package org.example;
import org.example.domain.Libro;
import org.example.exception.LibroNoEncontrado;
import org.example.exception.PrestamoNoEncontrado;
import org.example.repository.LibroRepository;
import org.example.repository.PrestamoRepository;
import org.example.service.LibroService;
import org.example.service.PrestamoService;

public class Main {
    public static void main(String[] args) {
        LibroRepository libroRepository = new LibroRepository();
        LibroService libroService = new LibroService(libroRepository);

        PrestamoRepository prestamoRepository = new PrestamoRepository();
        PrestamoService prestamoService = new PrestamoService(prestamoRepository,libroRepository);

        // Creación de data para libro.
        libroService.registrarLibro(
                1,
                "Adam Smith",
                "Java para principantes",
                "Java es un lenguaje complejo pero acá te lo enseñamos",
                10
        );
        libroService.registrarLibro(
                2,
                "Cristian de la fuente",
                "Go para principantes",
                "Go es un lenguaje complejo pero acá te lo enseñamos",
                10
        );
        libroService.registrarLibro(
                3,
                "Erika Jardian",
                "Python para principantes",
                "Python es un lenguaje complejo pero acá te lo enseñamos",
                10
        );

        try{
            prestamoService.registrarPrestamo(1, 3);
            System.out.println("Prestamo recién creado");
            System.out.println(prestamoService.consultarPrestamo(1));
            //System.out.println(libroService.consultarTitulo("Python para principantes"));
            prestamoService.devolverLibro(1);
            System.out.println("Prestamo ya devuelto");
            System.out.println(prestamoService.consultarPrestamo(1));
        } catch (LibroNoEncontrado | IllegalStateException | PrestamoNoEncontrado e){
            System.out.println(e.getMessage());
        }
    }
}