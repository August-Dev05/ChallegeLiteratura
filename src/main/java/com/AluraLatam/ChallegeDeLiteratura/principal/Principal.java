package com.AluraLatam.ChallegeDeLiteratura.principal;

import com.AluraLatam.ChallegeDeLiteratura.model.Libro;
import com.AluraLatam.ChallegeDeLiteratura.model.Autor;
import com.AluraLatam.ChallegeDeLiteratura.repository.AutorRepository;
import com.AluraLatam.ChallegeDeLiteratura.repository.LibroRepository;
import com.AluraLatam.ChallegeDeLiteratura.service.GutendexService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private final GutendexService gutendexService;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public Principal(GutendexService gutendexService, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.gutendexService = gutendexService;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n---- MENÚ ----");
            System.out.println("************************************");
            System.out.println("1 - Buscar libro por título");
            System.out.println("2 - Listar libros guardados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos en un año");
            System.out.println("5 - Listar libros por idioma");
            System.out.println("0 - Salir");
            System.out.println("************************************");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // consumir salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    List<Libro> libros = gutendexService.buscarLibrosPorTitulo(titulo);
                    libros.forEach(System.out::println);
                }
                case 2 -> {
                    List<Libro> libros = libroRepository.findAll();
                    libros.forEach(System.out::println);
                }
                case 3 -> {
                    List<Autor> autores = autorRepository.findAll();
                    autores.forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Ingrese el año a consultar: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine();
                    List<Autor> autoresVivos = autorRepository.findAll().stream()
                            .filter(a -> a.getNacimiento() != null && a.getNacimiento() <= anio &&
                                    (a.getFallecimiento() == null || a.getFallecimiento() > anio))
                            .toList();
                    autoresVivos.forEach(System.out::println);
                }
                case 5 -> {
                    System.out.print("Ingrese el código de idioma (ej: 'es', 'en', 'fr'): ");
                    String idioma = scanner.nextLine();
                    List<Libro> libros = libroRepository.findByIdioma(idioma);
                    libros.forEach(System.out::println);
                }
                case 0 -> {
                    System.out.println("¡Cerrando App!");
                    System.exit(0);
                }
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
