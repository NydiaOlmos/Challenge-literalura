package com.naomi.literalura.repository;

import com.naomi.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Libro findByTitulo(String nombreLibro);

    Libro findByDescargas(Integer descargas);

    @Query("SELECT l FROM Libro l WHERE l.lenguajes ILIKE %:idioma%")
    List<Libro> librosPorIdioma(String idioma);
}
