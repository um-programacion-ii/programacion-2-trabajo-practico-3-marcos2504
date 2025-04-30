package ar.edu.um;

import java.time.LocalDate;

public class Prestamo {
    private final Libro libro;
    private final LocalDate fechaPrestamo;

    public Prestamo(Libro libro) {
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        libro.setEstado(Estado.PRESTADO);
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
}