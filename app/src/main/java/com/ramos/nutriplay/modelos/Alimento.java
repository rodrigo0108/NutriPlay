package com.ramos.nutriplay.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Alimento implements Parcelable{
    private String beneficios,categoria,imagen,imagen_oscura,nombre,id;

    public Alimento(Parcel in) {
        beneficios = in.readString();
        categoria = in.readString();
        imagen = in.readString();
        imagen_oscura = in.readString();
        nombre = in.readString();
        id = in.readString();
    }

    public static final Creator<Alimento> CREATOR = new Creator<Alimento>() {
        @Override
        public Alimento createFromParcel(Parcel in) {
            return new Alimento(in);
        }

        @Override
        public Alimento[] newArray(int size) {
            return new Alimento[size];
        }
    };

    public Alimento(String beneficios, String categoria, String imagen, String imagen_oscura, String nombre,String id) {
        this.beneficios = beneficios;
        this.categoria = categoria;
        this.imagen = imagen;
        this.imagen_oscura = imagen_oscura;
        this.nombre = nombre;
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(beneficios);
        dest.writeString(categoria);
        dest.writeString(imagen);
        dest.writeString(imagen_oscura);
        dest.writeString(nombre);
        dest.writeString(id);
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
