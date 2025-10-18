package com.saberpro.model;

public class Materia {

    private String nombre;
    private int puntaje;
    private int nivel;

    public Materia() {}

    public Materia(String nombre, int puntaje, int nivel) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
