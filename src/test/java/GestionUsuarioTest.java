import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ar.edu.um.*;
class GestionUsuarioTest {

    @Mock
    private Catalogo catalogo;

    @Mock
    private SistemaPrestamos sistemaPrestamos;

    @InjectMocks
    private GestionUsuario gestionUsuarios;

    private final String USUARIO = "usuario1";
    private final String ISBN = "9000";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuario_Exitoso() {
        gestionUsuarios.registrarUsuario(USUARIO);
    }

    @Test
    void testRegistrarUsuario_Repetido() {
        gestionUsuarios.registrarUsuario(USUARIO);
        assertThrows(IllegalArgumentException.class, () ->
                gestionUsuarios.registrarUsuario(USUARIO)
        );
    }

    @Test
    void testRegistrarPrestamo_Exitoso() {
        Usuario usuario = new Usuario(USUARIO);
        when(catalogo.buscarPorIsbn(ISBN)).thenReturn(new Libro(ISBN, "Clean Code", "Robert C. Martin",Estado.DISPONIBLE));
        when(sistemaPrestamos.prestarLibro(ISBN)).thenReturn(new Prestamo(new Libro(ISBN, "Clean Code", "Robert C. Martin",Estado.DISPONIBLE)));

        // Primero registramos el usuario
        gestionUsuarios.registrarUsuario(USUARIO);

        // Ahora registramos el prestamo
        gestionUsuarios.registrarPrestamo(USUARIO, ISBN);

        verify(sistemaPrestamos).prestarLibro(ISBN);
    }

    @Test
    void testRegistrarPrestamo_UsuarioNoExiste() {
        assertThrows(IllegalArgumentException.class, () ->
                gestionUsuarios.registrarPrestamo(USUARIO, ISBN)
        );
    }
}

