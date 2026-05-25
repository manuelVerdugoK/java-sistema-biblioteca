package modules.prestamoModuleTests;

import org.example.domain.Libro;
import org.example.domain.Prestamo;
import org.example.repository.LibroRepository;
import org.example.repository.PrestamoRepository;
import org.example.service.PrestamoService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrestamoServiceTest {

    @Mock
    PrestamoRepository prestamoRepository;
    @Mock
    LibroRepository libroRepository;
    @InjectMocks
    PrestamoService prestamoService;

    int id = 1;
    String autor = "Madam Curie";
    String titulo = "Radioactivité";
    String descripcion = "Publicado en 1910, el 'Tratado sobre la radiactividad'" +
            " (Traité de Radioactivité) de Marie Curie es una obra fundamental " +
            "de dos volúmenes. Considerado una síntesis clásica de la física de" +
            " principios del siglo XX, el libro recopiló de forma exhaustiva " +
            "los experimentos, descubrimientos y la teoría detrás de la radiactividad";
    int stock = 10;

    Libro libro = new Libro(id, autor, titulo, descripcion, stock);
    Prestamo prestamo = new Prestamo(id, libro);

    @Test
    void registrarPrestamo_casoPositivo_RetornaVoid(){
        when(libroRepository.buscarLibro(id)).thenReturn(Optional.of(libro));
        prestamoService.registrarPrestamo(id,id);
        verify(prestamoRepository).guardarPrestamo(any(Prestamo.class));
    }

    @Test
    void consultarPrestamo_casoPositivo_RetornaString(){
        when(prestamoRepository.buscarPrestamo(id)).thenReturn(Optional.of(prestamo));
        assertEquals(prestamo.toString(), prestamoService.consultarPrestamo(id));
    }

    @Test
    void devolverLibro_casoPositivo_RetornaVoid(){
        libro.incrementarPrestados();
        when(prestamoRepository.buscarPrestamo(id)).thenReturn(Optional.of(prestamo));
        prestamoService.devolverLibro(id);
        assertEquals(0, libro.getContadorPrestados());
    }

}
