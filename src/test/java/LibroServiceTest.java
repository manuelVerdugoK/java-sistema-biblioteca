import org.example.domain.Libro;
import org.example.exception.LibroNoEncontrado;
import org.example.repository.LibroRepository;
import org.example.service.LibroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    LibroRepository libroRepository;

    @InjectMocks
    LibroService libroService;

    int id = 1;
    String autor = "Madam Curie";
    String titulo = "Radioactivité";
    String descripcion = "Publicado en 1910, el 'Tratado sobre la radiactividad'" +
            " (Traité de Radioactivité) de Marie Curie es una obra fundamental " +
            "de dos volúmenes. Considerado una síntesis clásica de la física de" +
            " principios del siglo XX, el libro recopiló de forma exhaustiva " +
            "los experimentos, descubrimientos y la teoría detrás de la radiactividad";
    int stock = 10;

    @Test
    void registrarLibro_casoPositivo_RetornaLibro(){
        libroService.registrarLibro(id, autor, titulo, descripcion, stock);
        verify(libroRepository).guardarLibro(any(Libro.class));
    }

    @Test
    void prestarLibro_casoPositivo_AumentaPrestadoLibro(){
        Libro libro = new Libro(id, autor, titulo, descripcion, stock);
        when(libroRepository.buscarLibro(id)).thenReturn(Optional.of(libro));
        libroService.prestarLibro(id);
        assertEquals(1,libro.getContadorPrestados());
    }
    @Test
    void devolverLibro_casoPositivo_RetornoCorrecto() {
        Libro libro = new Libro(id, autor, titulo, descripcion, stock);
        libro.incrementarPrestados();
        when(libroRepository.buscarLibro(id)).thenReturn(Optional.of(libro));
        libroService.devolverLibro(id);
        assertEquals(0, libro.getContadorPrestados());
    }
    @Test
    void consultarLibro_casoPositivo_retornaString(){
        Libro libro = new Libro(id, autor, titulo, descripcion, stock);
        when(libroRepository.buscarLibro(titulo)).thenReturn(Optional.of(libro));
        assertEquals(libro.toString(), libroService.consultarTitulo(titulo));
    }

    @Test
    void devolverLibro_casoNegativo_RetornaException() {
        assertThrows(LibroNoEncontrado.class,
                () -> libroService.devolverLibro(1)
                );
    }

    @Test
    void consultarLibro_casoNegativo_RetornaException(){

        assertThrows(LibroNoEncontrado.class,
                ()-> libroService.consultarTitulo(titulo)
        );
    }
    @Test
    void listarLibros_casoPositivo_retornaLista(){
        Libro libro = new Libro(id, autor, titulo, descripcion, stock);
        ArrayList<Libro> lista = new ArrayList<>();
        lista.add(libro);
        when(libroRepository.listarTodos()).thenReturn(lista);
        assertEquals(1, libroService.listarLibros().size());

    }
}
