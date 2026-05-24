import org.example.domain.Libro;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {
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


        libro = new Libro(id,autor,titulo,descripcion,stock);
    }
    @AfterEach
    void tearDown(){
        libro = null;
    }

    @Test
    void getId_casoPositivo_retornaIdCorrecto(){
        assertEquals(id,libro.getIdLibro());
    }
    @Test
    void getAutor_casoPositivo_retornaAutorCorrecto(){
        assertEquals(autor,libro.getAutor());
    }

    @Test
    void setAutor_casoPositivo_retornaNuevoAutorCorrecto(){
        String nuevoAutor = "Jane Doe";
     libro.setAutor(nuevoAutor);
     assertEquals(nuevoAutor,libro.getAutor());
    }

    @Test
    void getTitulo_casoPositivo_retornaTituloCorrecto(){
        assertEquals(titulo,libro.getTitulo());
    }
    @Test
    void setTitulo_casoPositivo_retornaTituloCorrecto(){
        String nuevoTitulo = "Java es lo máximo";
        libro.setTitulo(nuevoTitulo);
        assertEquals(nuevoTitulo,libro.getTitulo());
    }

    @Test
    void getDescripcion_casoPositivo_retornaDescripcionCorrecta(){
        assertEquals(descripcion,libro.getDescripcion());
    }
    @Test
    void setDescripcion_casoPositivo_retornaDescripcionCorrecta(){
        String nuevaDescripcion = "Java es lo máximo";
        libro.setDescripcion(nuevaDescripcion);
        assertEquals(nuevaDescripcion,libro.getDescripcion());
    }

    @Test
    void getStock_casoPositivo_retornaStockCorrecto(){
        assertEquals(stock,libro.getStock());
    }
    @Test
    void setStock_casoPositivo_retornaStockCorrecto(){
        int nuevoStock = 15;
        libro.setStock(nuevoStock);
        assertEquals(nuevoStock,libro.getStock());
    }

    @Test
    void getContadorPrestados_casoPositivo_retornaContadorPrestadoCorrecto(){
        assertEquals(0,libro.getContadorPrestados());
    }

    @Test
    void incrementarPrestados_conStockDisponible_incrementaContador(){
        int inicial = libro.getContadorPrestados();
        libro.incrementarPrestados();
        assertEquals(inicial+1,libro.getContadorPrestados());
    }

    @Test
    void incrementarPrestados_stockAgotado_lanzaIllegalStateException(){
        int stock = libro.getStock();
        for (int i=0;i<libro.getStock();i++){libro.incrementarPrestados();}
        assertThrows(
                IllegalStateException.class, ()-> libro.incrementarPrestados()
        );
    }

    @Test
    void decrementarPrestados_ContadorPrestadosMinimo_lanzaIllegalStateException(){
        assertThrows(
                IllegalStateException.class, ()-> libro.decrementarPrestados()
        );
    }

}
