package com.ramos.nutriplay.modelos;

public class ValorNutricional {
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
}
