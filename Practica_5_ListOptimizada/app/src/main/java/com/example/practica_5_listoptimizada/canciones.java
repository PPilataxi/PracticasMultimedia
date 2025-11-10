package com.example.practica_5_listoptimizada;

public class canciones {
    private String titulo;
    private String autor;
    private String image;

    public canciones(String titulo, String autor, String image){
        this.titulo = titulo;
        this.autor = autor;
        this.image = image;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getImage() {
        return image;
    }
}