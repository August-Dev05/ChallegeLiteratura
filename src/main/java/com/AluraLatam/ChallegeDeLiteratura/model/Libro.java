package com.AluraLatam.ChallegeDeLiteratura.model;

import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    private Long id;

    private String titulo;
    private String idioma;
    private Integer descargas;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return String.format("""
            --------------------------------------------
            Título   : %s
            Autor    : %s
            Idioma   : %s
            Descargas: %d
            ID       : %d
            """, titulo, autor != null ? autor.getNombre() : "Desconocido",
                idioma, descargas != null ? descargas : 0, id);
    }

}
