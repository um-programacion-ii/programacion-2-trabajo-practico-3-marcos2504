package ar.edu.um;

import java.util.HashMap;
import java.util.Map;
import ar.edu.um.*;
public class GestionUsuario {
    private final Catalogo catalogo;
    private final SistemaPrestamos sistemaPrestamos;
    private final Map<String, Usuario> usuarios = new HashMap<>();

    public GestionUsuario(Catalogo catalogo, SistemaPrestamos sistemaPrestamos) {
        this.catalogo = catalogo;
        this.sistemaPrestamos = sistemaPrestamos;
    }


    public void registrarUsuario(String nombre) {
        if (usuarios.containsKey(nombre)) {
            throw new IllegalArgumentException("Usuario ya registrado: " + nombre);
        }
        usuarios.put(nombre, new Usuario(nombre));
    }

    public void registrarPrestamo(String nombreUsuario, String isbn) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado: " + nombreUsuario);
        }
        Prestamo prestamo = sistemaPrestamos.prestarLibro(isbn);
        usuario.agregarPrestamo(prestamo);
    }
}
