import static org.junit.jupiter.api.Assertions.*;

import ar.edu.um.Catalogo;
import ar.edu.um.Estado;
import ar.edu.um.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class CatalogoTest {
    private Catalogo catalogo;
    private Libro libro1;
    private Libro libro2;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        libro1 = new Libro("9000", "El eternauta", "Héctor Germán Oesterheld", Estado.DISPONIBLE);
        libro2 = new Libro("8000", "Los viajes de Guliver", "Jonathan Swift", Estado.DISPONIBLE);
        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
    }

    @Test
    void testBuscarPorIsbn_Exitoso() {
        Libro encontrado = catalogo.buscarPorIsbn("9000");
        assertNotNull(encontrado);
        assertEquals("El eternauta", encontrado.getTitulo());
    }

    @Test
    void testBuscarPorIsbn_Fallido() {
        assertNull(catalogo.buscarPorIsbn("000-0"));
    }

    @Test
    void testObtenerTodosLosLibrosDisponibles() {
        libro2.setEstado(Estado.PRESTADO);
        List<Libro> disponibles = catalogo.obtenerTodosLosLibrosDisponibles();
        assertEquals(1, disponibles.size());
        assertEquals(libro1.getIsbn(), disponibles.get(0).getIsbn());
    }
}
