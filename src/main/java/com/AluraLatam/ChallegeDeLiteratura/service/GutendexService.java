package com.AluraLatam.ChallegeDeLiteratura.service;

import com.AluraLatam.ChallegeDeLiteratura.model.Autor;
import com.AluraLatam.ChallegeDeLiteratura.model.Libro;
import com.AluraLatam.ChallegeDeLiteratura.repository.LibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GutendexService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final LibroRepository libroRepository;

    public GutendexService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        List<Map<String, Object>> resultados = (List<Map<String, Object>>) response.getBody().get("results");

        List<Libro> libros = new ArrayList<>();

        for (Map<String, Object> libroJson : resultados) {
            Libro libro = new Libro();
            libro.setId(((Number) libroJson.get("id")).longValue());
            libro.setTitulo((String) libroJson.get("title"));

            List<String> idiomas = (List<String>) libroJson.get("languages");
            libro.setIdioma(idiomas.isEmpty() ? "N/A" : idiomas.get(0));

            libro.setDescargas((Integer) libroJson.get("download_count"));

            List<Map<String, Object>> autores = (List<Map<String, Object>>) libroJson.get("authors");
            if (!autores.isEmpty()) {
                Map<String, Object> autorJson = autores.get(0);
                Autor autor = new Autor();
                autor.setNombre((String) autorJson.get("name"));
                autor.setNacimiento((Integer) autorJson.get("birth_year"));
                autor.setFallecimiento((Integer) autorJson.get("death_year"));
                libro.setAutor(autor);
            }

            if (libro.getTitulo() == null || libro.getTitulo().isBlank()) continue;
            if (libro.getIdioma() == null || libro.getIdioma().isBlank()) continue;

            // Guardar libro con autor en DB
            if (!libroRepository.existsById(libro.getId())) {
                libroRepository.save(libro);
            }


            libros.add(libro);


        }

        return libros;
    }
}
