package com.ramos.nutriplay.modelos;

public class Pais {
    private String bandera,nombre;

    public Pais() {
    }

    public Pais(String bandera, String nombre) {
        this.bandera = bandera;
        this.nombre = nombre;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "bandera='" + bandera + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
