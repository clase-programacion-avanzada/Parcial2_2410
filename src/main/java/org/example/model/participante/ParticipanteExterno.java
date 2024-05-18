package org.example.model.participante;

public class ParticipanteExterno extends Participante{

    private String Entidad;

    public ParticipanteExterno(String nombre, String correo, String Entidad) {
        super(nombre, correo);
        this.Entidad = Entidad;
    }

    public String getEntidad() {
        return Entidad;
    }

    public void setEntidad(String Entidad) {
        this.Entidad = Entidad;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + Entidad;
    }


}
