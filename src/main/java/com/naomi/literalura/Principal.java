package com.naomi.literalura;

import com.naomi.literalura.model.*;
import com.naomi.literalura.repository.AutorRepository;
import com.naomi.literalura.repository.LibroRepository;
import com.naomi.literalura.service.ConsumoAPI;
import com.naomi.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversorDatos = new ConvierteDatos();
    private final Scanner entradaDatos = new Scanner(System.in);
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public Principal (LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0) {
            String menu = """
                    
                    Elija la opción a través de su número:
                    1- Buscar libro por título(API)
                    2- Lista libros registrados
                    3- Lista autores registrados
                    4- Lista autores vivos en un determinado año
                    5- Lista libros por idioma
                    6- Estadísticas de descarga
                    7- Top 10 libros más descargados
                    8- Buscar autor por nombre
                    9- Lista autores por cantidad de libros escritos
                    
                    0- Salir
                    """;

            System.out.println(menu);
            opcion = Integer.parseInt(entradaDatos.nextLine());

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostrarTodosLibros();
                    break;
                case 3:
                    mostrarTodosAutores();
                    break;
                case 4:
                    mostrarAutoresEpoca();
                    break;
                case 5:
                    mostrarLibroIdioma();
                    break;
                case 6:
                    mostrarEstadisticasDescargas();
                    break;
                case 7:
                    top10Libros();
                    break;
                case 0:
                    System.out.println("Terminando programa...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    // Metodos extras
    private void agregarAutores(DatosLibro datosLibro, Libro libroResultado){
        List<Autor> autoresLibro = new ArrayList<>();
        for(DatosAutor datosAutor : datosLibro.autores()){
            Autor autorExistente = autorRepository.findByNombre(datosAutor.nombre());
            if(autorExistente != null) {
                autoresLibro.add(autorExistente);
            } else {
                Autor nuevoAutor = new Autor(datosAutor);
                autoresLibro.add(nuevoAutor);
            }
        }
        libroResultado.setAutores(autoresLibro);
    }

    private void guardarLibrosAutores(Libro libro) {
        Libro libroExistente = libroRepository.findByTitulo(libro.getTitulo());
        if(libroExistente == null) {
            libroRepository.save(libro);
        } else {
            System.out.println("El libro no se a guardado porque ya existe dentro de la base de datos.");
        }
    }

    //Metodos Switch
    private void buscarLibro() {
        System.out.println("Escribe el nombre del libro que deseas búscar:");
        String nombreLibro = entradaDatos.nextLine();
        var json = consumoApi.consultaGutendex("/?search=" + nombreLibro.replace(" ", "+"));
        Datos libros = conversorDatos.obtenerDatos(json, Datos.class);
        DatosLibro datosLibro = libros.libros().getFirst();
//        System.out.println(datosLibro);
        Libro libroResultado = new Libro(datosLibro);
        agregarAutores(datosLibro, libroResultado);
        System.out.println(libroResultado);
        guardarLibrosAutores(libroResultado);
    }

    private void mostrarTodosLibros() {
        List<Libro> listaLibros = libroRepository.findAll();
        listaLibros.forEach(System.out::println);
    }

    private void mostrarTodosAutores() {
        List<Autor> listaAutores = autorRepository.findAll();
        listaAutores.forEach(System.out::println);
    }

    private void mostrarAutoresEpoca() {
        System.out.println("Ingrese el año:");
        int epoca = Integer.parseInt(entradaDatos.nextLine());
        List<Autor> autoresVivos = autorRepository.filtrarPorEpoca(epoca);
        if (!autoresVivos.isEmpty()) {
            autoresVivos.forEach(System.out::println);
        } else {
            System.out.println("No hay autores vivos en ese año.");
        }
    }

    private void mostrarLibroIdioma(){
        System.out.println("""
                
                Ingresa el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """);
        String idioma = entradaDatos.nextLine();
        List<Libro> librosIdioma = libroRepository.librosPorIdioma(idioma);
        if(!librosIdioma.isEmpty()) {
            librosIdioma.forEach(System.out::println);
        } else {
            System.out.println("No hay libros en ese idioma");
        }
    }

    private void mostrarEstadisticasDescargas() {
        List<Libro> libros = libroRepository.findAll();
        IntSummaryStatistics estadisticas = libros.stream()
                .filter(d -> d.getDescargas() > 0)
                .collect(Collectors.summarizingInt(Libro::getDescargas));

        String libroMasDescargado = (libroRepository.findByDescargas(estadisticas.getMax()).getTitulo());
        String libroMenosDescargado = (libroRepository.findByDescargas(estadisticas.getMin()).getTitulo());

        System.out.println("Media de descargas: " + estadisticas.getAverage());
        System.out.println("El libro con el mayor número de descargas: \"" + libroMasDescargado +
                "\" con " + estadisticas.getMax() +" descargas.");
        System.out.println("El libro con el menor número de descargas: \"" + libroMenosDescargado +
                "\" con " + estadisticas.getMin() +" descargas.");
        System.out.println("Total de registros: " + estadisticas.getCount());
        System.out.println("Total de descargas: " + estadisticas.getSum());
    }

    private void top10Libros() {
        List<Libro> topLibros = libroRepository.top10Descargas();
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + ".- " + topLibros.get(i-1).getTitulo() +
                    " con " + topLibros.get(i-1).getDescargas() + " descargas.");
        }
    }

    private void buscarAutorNombre() {}

    private void mostrarAutoresLibros() {}
}
