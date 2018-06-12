package com.ramos.nutriplay.modelos;

public class Minerales {
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
}
