import org.example.domain.Libro;
import org.example.repository.LibroRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class LibroRepositoryTest {
    LibroRepository libroRepository;
    Libro libro;

    int id = 1;
    String autor = "Madam Curie";
    String titulo = "Radioactivité";
    String descripcion = "Publicado en 1910, el 'Tratado sobre la radiactividad'" +
            " (Traité de Radioactivité) de Marie Curie es una obra fundamental " +
            "de dos volúmenes. Considerado una síntesis clásica de la física de" +
            " principios del siglo XX, el libro recopiló de forma exhaustiva " +
            "los experimentos, descubrimientos y la teoría detrás de la radiactividad";
    int stock = 10;

    @BeforeEach
    void Setup(){
        libroRepository = new LibroRepository();
        libro = new Libro(id, autor, titulo, descripcion, stock);
    }

    @AfterEach
    void tearDown(){
        libro = null;
        libroRepository = null;
    }

    @Test
    void guardarLibro_casoPositivo_GuardaCorrectamente(){
        assertEquals(libro, libroRepository.guardarLibro(libro));
    }

    @Test
    void buscarLibroId_casoPositivo_BusquedaExitosa(){
        libroRepository.guardarLibro(libro);
        assertTrue(libroRepository.buscarLibro(id).isPresent());
    }

    @Test
    void buscarLibroId_casoNegativo_retornaEmpty(){
        assertTrue(libroRepository.buscarLibro(id).isEmpty());
    }

    @Test
    void buscarLibroTitulo_casoPositivo_BusquedaExitosa(){
        libroRepository.guardarLibro(libro);
        assertTrue(libroRepository.buscarLibro(titulo).isPresent());
    }

    @Test
    void buscarLibroTitulo_casoNegativo_retornaEmpty(){
        assertTrue(libroRepository.buscarLibro(titulo).isEmpty());
    }

    @Test
    void listarTodos_casoPositivo_retornaArray(){
        libroRepository.guardarLibro(libro);
        Libro libro2 = new Libro(2,autor,titulo,descripcion,stock);
        libroRepository.guardarLibro(libro2);
        assertEquals(2, libroRepository.listarTodos().size());
    }
}
