package modules.prestamoModuleTests;

import org.example.domain.Libro;
import org.example.domain.Prestamo;
import org.example.repository.PrestamoRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoRepositoryTest {
    Prestamo prestamo;
    int prestamoId = 1;
    PrestamoRepository prestamoRepository;
    Libro libro = new Libro(
            1,
            "Autor-Test",
            "Titulo-Test",
            "Descripcion-Test",
            10
    );

    @BeforeEach
    void setup(){
        prestamo = new Prestamo(prestamoId, libro);
        prestamoRepository = new PrestamoRepository();
    }
    @AfterEach
    void tearDown(){
        prestamo = null;
        libro = null;
        prestamoRepository = null;
    }

    @Test
    void guardarPrestamo_casoPositivo_retornaVoid(){
        assertEquals(prestamo, prestamoRepository.guardarPrestamo(prestamo));
    }

    @Test
    void buscarLibro_casoPositivo_retornaOptionalPrestamo(){
        prestamoRepository.guardarPrestamo(prestamo);
        assertTrue(prestamoRepository.buscarPrestamo(prestamoId).isPresent());
    }

    @Test
    void buscarLibro_casoNegativo_retornaOptionalEmpty(){
        assertTrue(prestamoRepository.buscarPrestamo(prestamoId).isEmpty());
    }


    @Test
    void listarTodos_casoPositivo_retornaArrayList(){
        prestamoRepository.guardarPrestamo(prestamo);
        Prestamo prestamo2 = new Prestamo(2, libro);
        prestamoRepository.guardarPrestamo(prestamo2);
        assertEquals(2,prestamoRepository.listarTodos().size());
    }
}
