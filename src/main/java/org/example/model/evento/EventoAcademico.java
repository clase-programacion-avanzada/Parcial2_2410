package org.example.model.evento;

import java.io.Serializable;
import java.util.Date;
import org.example.model.participante.Participante;
import org.example.model.participante.ParticipanteInterno;

public class EventoAcademico extends Evento implements Serializable {

    public static final int TARIFA_FIJA = 10000;

    private String facultad;

    public EventoAcademico(String nombre, int capacidad, Date fecha , String facultad) {
        super(nombre, capacidad, fecha);
        this.facultad = facultad;
    }

    //2 Punto 4 parte 1: Implemente el método calcularCosto en EventoAcademico y EventoExterno.
    //Tenga en cuenta que en un evento académico se cobra uría tarifa fija por participante (TARIFA_FIJA),
    //pero aquellos que pertenecen a la facultad que organizó el evento no tienen cobro.
    // Por otro lado, los eventos externos tienen una tarifa por participante,
    // pero los participantes que pertenecen a la entidad patrocinadora tienen 50% de descuento sobre esa tarifa.
    @Override
    public int calcularCosto() {
        int total = 0;

        for (Participante p : participantes) {
            if (!(p instanceof ParticipanteInterno pi) || !pi.getFacultad().equals(facultad)) {
                total += TARIFA_FIJA;
            }
        }

        return total;
    }

    @Override
    public String reporte(){
        return super.reporte() + "Academico";
    }
}
