package org.example.model.evento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.example.model.participante.Participante;
import org.example.model.participante.ParticipanteExterno;

public abstract class Evento implements Serializable {

    private String nombre;
    private int capacidad;
    private Date fecha;

    protected List<Participante> participantes;

    public Evento(String nombre, int capacidad, Date fecha) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.fecha = fecha;
        this.participantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Evento) {
            Evento e = (Evento) obj;
            return e.getNombre().equals(nombre) && e.getFecha().equals(fecha);
        }
        return false;
    }


    public abstract int calcularCosto();

    @Override
    public String toString() {
        return nombre + " - " + fecha + " - " + capacidad + " - " + participantes;
    }

    //2 Punto 2 : Implemente un método en la clase Evento que registre un participante en el evento.
    // El método debe recibir por parámetro un objeto tipo Participante y agregarlo a la lista de participantes del evento.
    // Debe arrojar excepción
    // si el evento ya llegó a su capacidad límite,
    // si se intentó inscribir un participante externo en un evento académico,
    // o si ya existe un participante registrado con el mismo correo electrónico.
    public void agregarParticipante(Participante participante) {

        if (participantes.size() >= capacidad) {
            throw new IllegalArgumentException("El evento ya alcanzó su capacidad máxima");
        }

        if (this instanceof EventoAcademico && participante instanceof ParticipanteExterno) {
            throw new IllegalArgumentException("No se puede agregar un participante externo a un evento académico");
        }

        if (participanteYaEstaRegistrado(participante)) {
            throw new IllegalArgumentException("El participante ya está registrado en el evento");
        }

        participantes.add(participante);
    }

    public boolean participanteYaEstaRegistrado(Participante participante) {
        for (Participante p : participantes) {
            if (p.getCorreo().equals(participante.getCorreo())) {
                return true;
            }
        }
        return false;
    }

    public List<Participante> getParticipantes() {
        return new ArrayList<>(participantes);
    }

    public String reporte(){
        return "Evento: " + nombre + "\n"
            + "Fecha: " + fecha + "\n"
            + "Capacidad: " + capacidad + " participantes \n"
            + "Tipo: ";
    }

    public List<String> obtenerReporteParticipantes() {

        List<String> reporte = new ArrayList<>();
        for (Participante p : participantes) {
            reporte.add(p.toString());
        }
        return reporte;
    }
}
