package org.example.domain;
public class Libro {
    private final int idLibro;
    private String autor;
    private String titulo;
    private String descripcion;
    private int stock;
    private int contadorPrestados;

    public Libro(int idLibro, String autor, String titulo, String descripcion, int stock) {
        this.idLibro = idLibro;
        this.autor = autor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.contadorPrestados = 0;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", contadorPrestados=" + contadorPrestados +
                '}';
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getContadorPrestados() {
        return contadorPrestados;
    }

    private void setContadorPrestados(int numero) {
        this.contadorPrestados = numero;
    }

    public void incrementarPrestados() {
        if (getContadorPrestados()<getStock()){
            setContadorPrestados(
                    getContadorPrestados() + 1
            );
        } else {
            throw new IllegalStateException("No hay stock disponible");
        }

    }

    public void decrementarPrestados() {
        if (getContadorPrestados()>0){
            setContadorPrestados(
                    getContadorPrestados() - 1
            );
        } else {
            throw new IllegalStateException("No hay préstamos activos para devolver.");
        }
    }
}
