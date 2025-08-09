package com.naomi.literalura.repository;

import com.naomi.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Autor findByNombre(String nombreAutor);

    Autor findByNombreContaining(String nombre);

    @Query(value = "SELECT a FROM Autor a WHERE a.fechaNacimiento <= :epoca AND a.fechaDefuncion >= :epoca")
    List<Autor> filtrarPorEpoca(Integer epoca);


    //  Filtra según la cantidad de libros que un autor ha escrito un libro
    @Query(value = "SELECT a FROM Autor a JOIN a.libros l GROUP BY a HAVING COUNT(l) >= :minimo")
    List<Autor> busquedaMinimoLibros(Integer minimo);
}
