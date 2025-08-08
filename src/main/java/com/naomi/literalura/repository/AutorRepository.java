package com.naomi.literalura.repository;

import com.naomi.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Autor findByNombre(String nombreAutor);
}
