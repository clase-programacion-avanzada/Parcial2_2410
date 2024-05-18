package org.example.model.participante;

import java.io.Serializable;

//2 Punto 1: Complete la clase Participante Interno. Para esto, declare toda la clase, un constructor por parámetros,
// y los métodos get y set para el atributo de facultad.
public class ParticipanteInterno extends Participante implements Serializable {

    private String facultad;

    public ParticipanteInterno(String nombre, String correo, String facultad) {
        super(nombre, correo);
        this.facultad = facultad;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + facultad;
    }
}
