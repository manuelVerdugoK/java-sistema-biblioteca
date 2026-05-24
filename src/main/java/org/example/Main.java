package org.example;
import org.example.repository.LibroRepository;
import org.example.service.LibroService;
public class Main {
    public static void main(String[] args) {
        LibroRepository libroRepository = new LibroRepository();
        LibroService service = new LibroService(libroRepository);

        // Crear libro
        service.registrarLibro(
                1,
                "Adam Smith",
                "Java para principantes",
                "Java es un lenguaje complejo pero acá te lo enseñamos",
                10
        );
        service.registrarLibro(
                2,
                "Cristian de la fuente",
                "Go para principantes",
                "Go es un lenguaje complejo pero acá te lo enseñamos",
                10
        );
        service.registrarLibro(
                3,
                "Erika Jardian",
                "Python para principantes",
                "Python es un lenguaje complejo pero acá te lo enseñamos",
                10
        );
    }
}