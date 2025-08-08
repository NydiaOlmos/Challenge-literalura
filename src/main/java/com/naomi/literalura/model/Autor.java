package com.naomi.literalura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaDefuncion;
    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        this.fechaNacimiento = autor.fechaNacimiento();
        this.fechaDefuncion = autor.fechaDefuncion();
    }

    @Override
    public String toString() {
        StringBuilder librosString = new StringBuilder();
        libros.forEach(a -> librosString.append(a.getTitulo()).append("; "));
        return "\n---- Autor ----" +
                "\nNombre: " + nombre +
                "\nFecha de nacimiento: " + fechaNacimiento +
                "\nFecha de fallecimiento: " + fechaDefuncion +
                "\nLibros: " + librosString +
                "\n---------------";
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public Integer getFechaNacimiento() {return fechaNacimiento;}

    public void setFechaNacimiento(Integer fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public Integer getFechaDefuncion() {return fechaDefuncion;}

    public void setFechaDefuncion(Integer fechaDefuncion) {this.fechaDefuncion = fechaDefuncion;}

    public List<Libro> getLibros() {return libros;}

    public void setLibros(List<Libro> libros) {this.libros = libros;}
}
