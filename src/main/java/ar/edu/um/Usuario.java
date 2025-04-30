package ar.edu.um;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final String nombre;
    private final List<Prestamo> historialPrestamos = new ArrayList<>();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Prestamo> getHistorialPrestamos() {
        return historialPrestamos;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        historialPrestamos.add(prestamo);
    }
}