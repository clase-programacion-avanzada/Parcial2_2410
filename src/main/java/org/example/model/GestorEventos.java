package org.example.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.example.model.evento.Evento;
import org.example.model.participante.Participante;

public class GestorEventos {

    public final List<Evento> eventos;

    public GestorEventos() {
        this.eventos = new ArrayList<>();
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    public Evento buscarEventoPorNombre(String nombre) {
        for (Evento evento : eventos) {
            if (evento.getNombre().equals(nombre)) {
                return evento;
            }
        }
        return null;
    }

    public List<Evento> buscarEventosPorFecha(Date fecha) {
        List<Evento> eventosEnFecha = new ArrayList<>();

        for (Evento evento : eventos) {
            if (evento.getFecha().compareTo(fecha) == 0) {
                eventosEnFecha.add(evento);
            }
        }

        return eventosEnFecha;
    }

    public List<Evento> buscarEventosPorFechas(Date fechaInicio, Date fechaFin) {
        List<Evento> eventosEnFecha = new ArrayList<>();

        for (Evento evento : eventos) {
            if (evento.getFecha().after(fechaInicio) && evento.getFecha().before(fechaFin)) {
                eventosEnFecha.add(evento);
            }
        }

        return eventosEnFecha;
    }
    //2 Punto 3: Implemente un método agregarParticipante en la clase GestorEventos que reciba por parámetro el nombre del evento (no hay eventos con nombre repetido)
    // y un objeto que represente el participante, y ágregar el participante a al lista de participantes del evento.
    // Debe arrojar una excepción en cualquiera de los siguientes casos:
    //a) El evento no existe
    //b) Se intentó inscribir un participante externo en un evento académico
    //c) El participante ya está registrado en otro evento en esa inis- ma fecha
    //d) El participante ya estaba registrado en el evento e) El evento ya alcanzó su capacidad límite
    public void agregarParticipante(String nombreEvento, Participante participante) {
        Evento evento = buscarEventoPorNombre(nombreEvento);
        if (evento == null) {
            throw new IllegalArgumentException("No se encontró el evento");
        }

        if (participanteEstaInscritoEnOtroEvento(evento,participante)) {
            throw new IllegalArgumentException("El participante ya está inscrito en otro evento en esta fecha");
        }

        evento.agregarParticipante(participante);
    }

    private boolean participanteEstaInscritoEnOtroEvento(Evento evento, Participante participante) {

        Date fecha = evento.getFecha();
        List<Evento> eventosEnFecha = buscarEventosPorFecha(fecha);

        for (Evento eventoEnFecha : eventosEnFecha) {
            if (!eventoEnFecha.equals(evento) && eventoEnFecha.participanteYaEstaRegistrado(participante)) {
                return true;
            }
        }

        return false;

    }

    //2 Punto 5: Implemente un método serializar en la clase GestorEventos que almacene todos los eventos registrados en un archivo serializado.
    // Este método debe recibir por parámetro un String con la ruta del archivo en donde se quiere guardar la información.
    // Puede asumir que todas las clases del sistema implementan la interfaz Serializable.
    public void serializar(String ruta)  throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(eventos);
        }

    }

    //2 Punto 6: Implemente método deserializar en la clase GestorEventos que cargue los eventos de un archivo serializado.
    // En caso de ya tener eventos registrados, se deben eliminar.
    // Este método debe recibir por parámetro un String con la ruta del archivo del que se quiere cargar la información.
    // Puede asumir que todas las clases del sistema implementan la interfaz Serializable.
    public void deserializar(String ruta) throws IOException, ClassNotFoundException {

        if (!eventos.isEmpty()) {
            eventos.clear();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            eventos.addAll((List<Evento>) ois.readObject());
        }
    }

    //2 Punto 7: Implemente un método reporteEventos en la clase GestorEventos que reciba la ruta a un archivo de texto e imprima en éste un reporte con el siguiente formato:
    //Evento: <Nombre>
    //Fecha: ‹Fecha>
    //Capacidad: <Capacidad> participantes
    //Tipo: <Académico/Externo>
    // Participantes:
    //   <Nombre> - ‹correo> (‹facultad/entidad>)
    //   ‹Nombre> - ‹correo> (<facultad/entidad>)
    //Evento: <Nombre>
    //Fecha: <Fecha>
    //Capacidad: <Capacidad> participantes
    //Tipo: ‹Académico/Externo>
    //Participantes:
    //   <Nombre> - ‹correo> (<facultad/entidad>)
    //   ‹Nombre> - ‹correo> (<facultad/entidad>)
    public void reporteEventos(String ruta) throws IOException {
        List<String> reporte = new ArrayList<>();

        for (Evento evento : eventos) {
            reporte.add (evento.reporte());

            for (Participante participante : evento.getParticipantes()) {
                reporte.add(participante.toString());
            }
        }

        File file = new File(ruta);

        Files.write(file.toPath(), reporte);
    }


    public List<Evento> getEventos() {
        return new ArrayList<>(eventos);
    }
}
