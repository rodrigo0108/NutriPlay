package com.ramos.nutriplay.modelos;

public class Estado{
    private boolean estado;

    public Estado(boolean estado) {
        this.estado = estado;
    }
    public Estado() {
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}