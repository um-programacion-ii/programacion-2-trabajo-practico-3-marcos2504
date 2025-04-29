import static org.junit.jupiter.api.Assertions.*;

import ar.edu.um.Estado;
import ar.edu.um.Libro;
import org.junit.jupiter.api.Test;

class LibroTest {

    @Test
    void testCrearLibroValido() {
        Libro libro = new Libro("9000", "El eternauta", "Héctor Germán Oesterheld",Estado.PRESTADO);
        assertEquals("9000", libro.getIsbn());
        assertEquals("El eternauta", libro.getTitulo());
        assertEquals("Héctor Germán Oesterheld", libro.getAutor());
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @Test
    void testCambioEstadoLibro() {
        Libro libro = new Libro("9000", "El eternauta", "Héctor Germán Oesterheld",Estado.PRESTADO);
        libro.setEstado(Estado.DISPONIBLE);
        assertEquals(Estado.DISPONIBLE , libro.getEstado());
    }
}