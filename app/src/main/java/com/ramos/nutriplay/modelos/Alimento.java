package com.ramos.nutriplay.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Alimento implements Parcelable{
    private String beneficios,categoria_alimento,categoria_valor,imagen,imagen_flat,imagen_oscura,nombre,origen,pais_productor,tips,id;



    private ValorNutricional valorNutricional;

    public Alimento() {
        //Super importante para que Firebase RealTime Database funcione
    }

    public Alimento(String beneficios, String categoria_alimento,String categoria_valor,String imagen, String imagen_flat, String imagen_oscura, String nombre, String origen, String pais_productor, String tips, String id, ValorNutricional valorNutricional) {
        this.beneficios = beneficios;
        this.categoria_alimento = categoria_alimento;
        this.categoria_valor = categoria_valor;
        this.imagen = imagen;
        this.imagen_flat = imagen_flat;
        this.imagen_oscura = imagen_oscura;
        this.nombre = nombre;
        this.origen = origen;
        this.pais_productor = pais_productor;
        this.tips = tips;
        this.id = id;
        this.valorNutricional = valorNutricional;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public String getCategoria_alimento() {
        return categoria_alimento;
    }

    public void setCategoria_alimento(String categoria_alimento) {
        this.categoria_alimento = categoria_alimento;
    }

    public String getCategoria_valor() {
        return categoria_valor;
    }

    public void setCategoria_valor(String categoria_valor) {
        this.categoria_valor = categoria_valor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen_flat() {
        return imagen_flat;
    }

    public void setImagen_flat(String imagen_flat) {
        this.imagen_flat = imagen_flat;
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

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getPais_productor() {
        return pais_productor;
    }

    public void setPais_productor(String pais_productor) {
        this.pais_productor = pais_productor;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ValorNutricional getValorNutricional() {
        return valorNutricional;
    }

    public void setValorNutricional(ValorNutricional valorNutricional) {
        this.valorNutricional = valorNutricional;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(beneficios);
        dest.writeString(categoria_alimento);
        dest.writeString(categoria_valor);
        dest.writeString(imagen);
        dest.writeString(imagen_flat);
        dest.writeString(imagen_oscura);
        dest.writeString(nombre);
        dest.writeString(origen);
        dest.writeString(pais_productor);
        dest.writeString(tips);
        dest.writeString(id);
        dest.writeParcelable(valorNutricional,flags);
    }

    public Alimento(Parcel in) {
        beneficios = in.readString();
        categoria_alimento = in.readString();
        categoria_valor = in.readString();
        imagen = in.readString();
        imagen_flat = in.readString();
        imagen_oscura = in.readString();
        nombre = in.readString();
        origen = in.readString();
        pais_productor = in.readString();
        tips = in.readString();
        id = in.readString();
        valorNutricional = in.readParcelable(getClass().getClassLoader());
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

}
