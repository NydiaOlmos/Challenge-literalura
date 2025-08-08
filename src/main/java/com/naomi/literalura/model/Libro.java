package com.naomi.literalura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private Integer id;
    private String titulo;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;
    private String lenguajes;
    private Integer descargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.id = datosLibro.id();
        this.titulo = datosLibro.titulo();
        this.lenguajes = String.join(",", datosLibro.lenguajes());
        this.descargas = datosLibro.descargas();
        this.autores = datosLibro.autores().stream()
                .map(Autor::new)
                .toList();
    }

    @Override
    public String toString() {
        StringBuilder autoresString = new StringBuilder();
        autores.forEach(a -> autoresString.append(a.getNombre()).append("; "));

        return "------ LIBRO ------" +
                "\nTitulo: " + titulo +
                "\nAutores: " + autoresString +
                "\nLenguajes: " + lenguajes +
                "\nDescargas: " + descargas +
                "\n-------------------";
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public List<Autor> getAutores() {return autores;}

    public void setAutores(List<Autor> autor) {this.autores = autor;}

    public String getLenguajes() {return lenguajes;}

    public void setLenguajes(String lenguajes) {this.lenguajes = lenguajes;}

    public Integer getDescargas() {return descargas;}

    public void setDescargas(Integer descargas) {this.descargas = descargas;}
}
