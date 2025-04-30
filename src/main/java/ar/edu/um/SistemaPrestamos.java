package ar.edu.um;

public class SistemaPrestamos {
    private final Catalogo catalogo;

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }


    public Prestamo prestarLibro(String isbn) {
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro == null) {
            throw new IllegalArgumentException("Libro no encontrado: " + isbn);
        }
        if (libro.getEstado() == Estado.PRESTADO) {
            throw new IllegalStateException("El libro ya está prestado: " + isbn);
        }
        return new Prestamo(libro);
    }
}
