package com.naomi.literalura;

import com.naomi.literalura.model.*;
import com.naomi.literalura.repository.AutorRepository;
import com.naomi.literalura.repository.LibroRepository;
import com.naomi.literalura.service.ConsumoAPI;
import com.naomi.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversorDatos = new ConvierteDatos();
    private final Scanner entradaDatos = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public void mostrarMenu(){
        String menu = """
                Elija la opción a través de su número:
                1- Buscar libro por título(API)
                2- Lista libros registrados
                3- Lista autores registrados
                4- Lista autores vivos en un determinado año
                5- Lista libros por idioma
                
                0- Salir
                """;

        System.out.println(menu);
        int opcion = Integer.parseInt(entradaDatos.nextLine());

        switch (opcion){
            case 1:
                buscarLibro();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0:
                System.out.println("Terminando programa...");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }

    private void buscarLibro() {
        System.out.println("Escribe el nombre del libro que deseas búscar:");
        String nombreLibro = entradaDatos.nextLine();
        var json = consumoApi.consultaGutendex("/?search=" + nombreLibro.replace(" ", "+"));
        Datos libros = conversorDatos.obtenerDatos(json, Datos.class);
        DatosLibro datosLibro = libros.libros().getFirst();
        System.out.println(datosLibro);
//        Libro libroResultado = guardarLibroConAutores(datosLibro);
        Libro libroResultado = new Libro(datosLibro);
        System.out.println(libroResultado);
        libroRepository.save(libroResultado);
    }



    private Libro guardarLibroConAutores(DatosLibro datosLibro) {
        Libro libro = new Libro(datosLibro);
        // Procesar cada autor
        for (DatosAutor datosAutor : datosLibro.autores()) {
            // Buscar si el autor ya existe
            Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                    .orElseGet(() -> {
                        Autor nuevoAutor = new Autor(datosAutor);
                        return autorRepository.save(nuevoAutor);
                    });

            // Establecer la relación desde el lado dueño (Libro)
            libro.agregarAutor(autor);
        }
        // Guardar el libro (esto propagará los cambios)
        libroRepository.save(libro);
        return libro;
    }
}
