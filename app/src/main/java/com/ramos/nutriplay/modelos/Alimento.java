package com.ramos.nutriplay.modelos;

public class Alimento {
    private String beneficios,categoria,imagen,imagen_oscura,nombre,id;

    public Alimento(String beneficios, String categoria, String imagen, String imagen_oscura, String nombre,String id) {
        this.beneficios = beneficios;
        this.categoria = categoria;
        this.imagen = imagen;
        this.imagen_oscura = imagen_oscura;
        this.nombre = nombre;
        this.id = id;
    }

    public Alimento() {
        //Necesario
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen_oscura() {
        return imagen_oscura;
    }

    public void setImagen_oscura(String imagen_oscura) {
        this.imagen_oscura = imagen_oscura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
