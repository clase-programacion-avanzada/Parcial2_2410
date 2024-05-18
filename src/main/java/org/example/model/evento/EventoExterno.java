package org.example.model.evento;

import java.io.Serializable;
import java.util.Date;
import org.example.model.participante.Participante;
import org.example.model.participante.ParticipanteExterno;

public class EventoExterno extends Evento implements Serializable {

    private String patrocinador;
    private int tarifaPorParticipante;

    public EventoExterno(String nombre, int capacidad, Date fecha, String patrocinador, int tarifaPorParticipante) {
        super(nombre, capacidad, fecha);
        this.patrocinador = patrocinador;
        this.tarifaPorParticipante = tarifaPorParticipante;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public int getTarifaPorParticipante() {
        return tarifaPorParticipante;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public void setTarifaPorParticipante(int tarifaPorParticipante) {
        this.tarifaPorParticipante = tarifaPorParticipante;
    }
    //2 Punto 4 parte 2: Implemente el método calcularCosto en EventoAcademico y EventoExterno.
    // Tenga en cuenta que en un evento académico se cobra uría tarifa fija por participante (TARIFA_FIJA),
    // pero aquellos que pertenecen a la facultad que organizó el evento no tienen cobro.
    // Por otro lado, los eventos externos tienen una tarifa por participante,
    // pero los participantes que pertenecen a la entidad patrocinadora tienen 50% de descuento sobre esa tarifa.
    @Override
    public int calcularCosto() {
        int total = 0;

        for (Participante p : participantes) {
            if (p instanceof ParticipanteExterno pe && pe.getEntidad().equals(patrocinador)) {
                total += tarifaPorParticipante*0.5;
            } else {
                total += tarifaPorParticipante;
            }
        }

        return total;

    }

    @Override
    public String reporte(){
        return super.reporte() + "Externo";
    }


}
