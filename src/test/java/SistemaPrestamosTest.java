import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ar.edu.um.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SistemaPrestamosTest {

    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    private final String ISBN = "9000";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrestarLibro_Exitoso() {
        Libro libro = new Libro(ISBN, "El eternauta", "Héctor Germán Oesterheld",Estado.DISPONIBLE);
        when(catalogo.buscarPorIsbn(ISBN)).thenReturn(libro);

        Prestamo prestamo = sistemaPrestamos.prestarLibro(ISBN);

        assertNotNull(prestamo);
        verify(catalogo).buscarPorIsbn(ISBN);
        assertEquals(Estado.PRESTADO, libro.getEstado());
        assertEquals(ISBN, prestamo.getLibro().getIsbn());
    }

    @Test
    void testPrestarLibro_LibroNoEncontrado() {
        when(catalogo.buscarPorIsbn(ISBN)).thenReturn(null);

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                sistemaPrestamos.prestarLibro(ISBN)
        );
        assertTrue(ex.getMessage().contains("Libro no encontrado"));
    }

    @Test
    void testPrestarLibro_LibroYaPrestado() {
        Libro libro = new Libro(ISBN, "El eternauta", "Héctor Germán Oesterheld",Estado.PRESTADO);
        when(catalogo.buscarPorIsbn(ISBN)).thenReturn(libro);

        assertThrows(IllegalStateException.class, () ->
                sistemaPrestamos.prestarLibro(ISBN)
        );
        verify(catalogo).buscarPorIsbn(ISBN);
    }
}
