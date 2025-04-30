package ar.edu.um;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo {
    private final List<Libro> libros = new ArrayList<>();

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public Libro buscarPorIsbn(String isbn) {
        return libros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Libro> obtenerTodosLosLibrosDisponibles() {
        return libros.stream()
                .filter(l -> l.getEstado() == Estado.DISPONIBLE)
                .collect(Collectors.toList());
    }
}
