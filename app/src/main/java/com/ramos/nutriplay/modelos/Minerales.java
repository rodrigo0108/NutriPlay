package com.ramos.nutriplay.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Minerales implements Parcelable{
    private String calcio,fosforo,hierro,magnesio,potasio,sodio;

    public Minerales() {
    }

    public Minerales(String calcio, String fosforo, String hierro, String magnesio, String potasio, String sodio) {
        this.calcio = calcio;
        this.fosforo = fosforo;
        this.hierro = hierro;
        this.magnesio = magnesio;
        this.potasio = potasio;
        this.sodio = sodio;
    }

    public String getCalcio() {
        return calcio;
    }

    public void setCalcio(String calcio) {
        this.calcio = calcio;
    }

    public String getFosforo() {
        return fosforo;
    }

    public void setFosforo(String fosforo) {
        this.fosforo = fosforo;
    }

    public String getHierro() {
        return hierro;
    }

    public void setHierro(String hierro) {
        this.hierro = hierro;
    }

    public String getMagnesio() {
        return magnesio;
    }

    public void setMagnesio(String magnesio) {
        this.magnesio = magnesio;
    }

    public String getPotasio() {
        return potasio;
    }

    public void setPotasio(String potasio) {
        this.potasio = potasio;
    }

    public String getSodio() {
        return sodio;
    }

    public void setSodio(String sodio) {
        this.sodio = sodio;
    }

    @Override
    public String toString() {
        return "Minerales{" +
                "calcio='" + calcio + '\'' +
                ", fosforo='" + fosforo + '\'' +
                ", hierro='" + hierro + '\'' +
                ", magnesio='" + magnesio + '\'' +
                ", potasio='" + potasio + '\'' +
                ", sodio='" + sodio + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(calcio);
        dest.writeString(fosforo);
        dest.writeString(hierro);
        dest.writeString(magnesio);
        dest.writeString(potasio);
        dest.writeString(sodio);
    }

    public Minerales(Parcel in) {
        calcio = in.readString();
        fosforo = in.readString();
        hierro = in.readString();
        magnesio = in.readString();
        potasio = in.readString();
        sodio = in.readString();
    }

    public static final Creator<Minerales> CREATOR = new Creator<Minerales>() {
        @Override
        public Minerales createFromParcel(Parcel in) {
            return new Minerales(in);
        }

        @Override
        public Minerales[] newArray(int size) {
            return new Minerales[size];
        }
    };
}
