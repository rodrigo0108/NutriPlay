package com.ramos.nutriplay.modelos;

public class Origen {
    private String imagen,nombre;

    public Origen() {
    }

    public Origen(String imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CategoriaAlimento{" +
                "imagen='" + imagen + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
