package modules.prestamoModuleTests;

import org.example.domain.EstadoPrestamo;
import org.example.domain.Libro;
import org.example.domain.Prestamo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrestamoTest {
    Prestamo prestamo;

    int prestamoId = 1;
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
    }

    @AfterEach
    void teardown(){
        prestamo = null;
    }

    @Test
    void getId_CasoPositivo_RetornaId(){
        assertEquals(prestamoId,prestamo.getId());
    }

    @Test
    void getLibro_CasoPositivo_RetornaLibro(){
        assertEquals(libro,prestamo.getLibro());
    }

    @Test
    void getEstado_CasoPositivo_RetornaEstadoPrestamo(){
        assertEquals(EstadoPrestamo.prestado,prestamo.getEstado());
    }

    @Test
    void setEstado_CasoPositivo_RetornaVoid(){
        EstadoPrestamo nuevoEstado = EstadoPrestamo.devuelto;
        prestamo.setEstado(nuevoEstado);
        assertEquals(nuevoEstado,prestamo.getEstado() );
    }
}
