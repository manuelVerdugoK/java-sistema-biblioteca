package org.example.domain;

public class Prestamo {
    private final int idPrestamo;
    private Libro libro;
    private EstadoPrestamo estado;

    public Prestamo (int idPrestamo, Libro libro){
        this.idPrestamo = idPrestamo;
        this.libro = libro;
        this.estado = EstadoPrestamo.prestado;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", libro=" + libro +
                ", estado=" + estado +
                '}';
    }

    public int getId(){return this.idPrestamo;}
    public Libro getLibro(){return this.libro;}
    public EstadoPrestamo getEstado(){return this.estado;}
    public void setEstado(EstadoPrestamo estado){ this.estado = estado;}

}
