package com.ramos.nutriplay.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class ValorNutricional implements Parcelable{
    private String calorias,fibra,proteinas;
    private Minerales minerales;
    private Vitaminas vitaminas;

    public ValorNutricional() {
    }

    public ValorNutricional(String calorias, String fibra, String proteinas, Minerales minerales, Vitaminas vitaminas) {
        this.calorias = calorias;
        this.fibra = fibra;
        this.proteinas = proteinas;
        this.minerales = minerales;
        this.vitaminas = vitaminas;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }

    public String getFibra() {
        return fibra;
    }

    public void setFibra(String fibra) {
        this.fibra = fibra;
    }

    public String getProteinas() {
        return proteinas;
    }

    public void setProteinas(String proteinas) {
        this.proteinas = proteinas;
    }

    public Minerales getMinerales() {
        return minerales;
    }

    public void setMinerales(Minerales minerales) {
        this.minerales = minerales;
    }

    public Vitaminas getVitaminas() {
        return vitaminas;
    }

    public void setVitaminas(Vitaminas vitaminas) {
        this.vitaminas = vitaminas;
    }

    @Override
    public String toString() {
        return "Valor_Nutricional{" +
                "calorias='" + calorias + '\'' +
                ", fibra='" + fibra + '\'' +
                ", proteinas='" + proteinas + '\'' +
                ", minerales=" + minerales.toString() +
                ", vitaminas=" + vitaminas.toString() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(calorias);
        dest.writeString(fibra);
        dest.writeString(proteinas);
        dest.writeParcelable(minerales,flags);
        dest.writeParcelable(vitaminas,flags);
    }

    public ValorNutricional(Parcel in) {
        calorias = in.readString();
        fibra = in.readString();
        proteinas = in.readString();
        minerales = in.readParcelable(getClass().getClassLoader());
        vitaminas = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<ValorNutricional> CREATOR = new Creator<ValorNutricional>() {
        @Override
        public ValorNutricional createFromParcel(Parcel in) {
            return new ValorNutricional(in);
        }

        @Override
        public ValorNutricional[] newArray(int size) {
            return new ValorNutricional[size];
        }
    };
}
