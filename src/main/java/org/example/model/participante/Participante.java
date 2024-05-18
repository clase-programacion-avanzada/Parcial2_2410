package org.example.model.participante;

import java.io.Serializable;

public abstract class Participante implements Serializable {

    private String nombre;
    private String correo;

    public Participante(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {

        return "    " + nombre + " - " + correo ;
    }

}
