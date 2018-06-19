package com.ramos.nutriplay.modelos;

public class CategoriaValor {
    private String tipo;
    private Integer moneda;

    public CategoriaValor() {
    }

    public CategoriaValor(String tipo, Integer moneda) {
        this.tipo = tipo;
        this.moneda = moneda;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return "CategoriaValor{" +
                "tipo='" + tipo + '\'' +
                ", moneda=" + moneda +
                '}';
    }
}
