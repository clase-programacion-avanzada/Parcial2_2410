package org.example;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import org.example.model.GestorEventos;
import org.example.model.evento.Evento;
import org.example.model.evento.EventoAcademico;
import org.example.model.evento.EventoExterno;
import org.example.model.participante.Participante;
import org.example.model.participante.ParticipanteExterno;
import org.example.model.participante.ParticipanteInterno;

public class Main {
    public static void main(String[] args) {




        Participante participanteExterno1 = new ParticipanteExterno("Juan", "juan@colpatria.com", "Colpatria");
        Participante participanteExterno2 = new ParticipanteExterno("Alejandro", "alealejandro@bancolombia.com", "Bancolombia");
        Participante participanteExterno3 = new ParticipanteExterno("Roberto", "roberto@globant.com", "Globant");
        Participante participanteExterno4 = new ParticipanteExterno("Anahi", "anahi@endava.com", "Endava");
        Participante participanteExterno5 = new ParticipanteExterno("Artemisa", "art@starbucks.com", "Starbucks");

        Participante participanteInterno1 = new ParticipanteInterno("Pedro", "pedropedropedro@javeriana.edu.co", "Facultad de Ingenieria");
        Participante participanteInterno2 = new ParticipanteInterno("Maria", "m@javeriana.edu.co", "Facultad de Ciencias");
        Participante participanteInterno3 = new ParticipanteInterno("Luis", "luigi@javeriana.edu.co", "Facultad de Medicina");
        Participante participanteInterno4 = new ParticipanteInterno("Charisandra", "chari@javeriana.edu.co", "Facultad de Ingeniería");
        Participante participanteInterno5 = new ParticipanteInterno("Jorge", "j@javeriana.edu.co", "Facultad de Negocios");

        Date hoy = Date.valueOf(LocalDate.now());
        Date unaSemanaDespuesDeHoy = Date.valueOf(LocalDate.now().plusWeeks(1));
        Date dosSemanasDespuesDeHoy = Date.valueOf(LocalDate.now().plusWeeks(2));
        Date unMesDespuesDeHoy = Date.valueOf(LocalDate.now().plusMonths(1));
        Date unAnioDespuesDeHoy = Date.valueOf(LocalDate.now().plusYears(1));

        Evento eventoAcademico1 = new EventoAcademico("Evento Academico 1", 3, hoy, "Facultad de Ingenieria");
        Evento eventoAcademico2 = new EventoAcademico("Evento Academico 2", 2, unaSemanaDespuesDeHoy, "Facultad de Ciencias");
        Evento eventoAcademico3 = new EventoAcademico("Evento Academico 3", 1, dosSemanasDespuesDeHoy, "Facultad de Medicina");
        Evento eventoAcademico4 = new EventoAcademico("Evento Academico 4", 4, unMesDespuesDeHoy, "Facultad de Ingenieria");
        Evento eventoAcademico5 = new EventoAcademico("Evento Academico 5", 5, unAnioDespuesDeHoy, "Facultad de Negocios");

        //5 eventos externos
        Evento eventoExterno1 = new EventoExterno("Evento Externo 1", 3, hoy, "Colpatria", 10000);
        Evento eventoExterno2 = new EventoExterno("Evento Externo 2", 2, unaSemanaDespuesDeHoy, "Bancolombia", 20000);
        Evento eventoExterno3 = new EventoExterno("Evento Externo 3", 1, dosSemanasDespuesDeHoy, "Globant", 30000);
        Evento eventoExterno4 = new EventoExterno("Evento Externo 4", 4, unMesDespuesDeHoy, "Endava", 40000);
        Evento eventoExterno5 = new EventoExterno("Evento Externo 5", 5, unAnioDespuesDeHoy, "Starbucks", 50000);

        GestorEventos gestorEventos = new GestorEventos();

        gestorEventos.agregarEvento(eventoAcademico1);
        gestorEventos.agregarEvento(eventoAcademico2);
        gestorEventos.agregarEvento(eventoAcademico3);
        gestorEventos.agregarEvento(eventoAcademico4);
        gestorEventos.agregarEvento(eventoAcademico5);


        gestorEventos.agregarEvento(eventoExterno1);
        gestorEventos.agregarEvento(eventoExterno2);
        gestorEventos.agregarEvento(eventoExterno3);
        gestorEventos.agregarEvento(eventoExterno4);
        gestorEventos.agregarEvento(eventoExterno5);

        //Al agregar un partticipante a un evento que no existe, se lanza una excepcion

        try {
            System.out.println("Agregando participante a evento no existente");
            gestorEventos.agregarParticipante("Evento Academico No existente", participanteExterno1);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=============================================");

        // Al agregar un participante externo a un evento academico, se lanza una excepcion
        try {
            System.out.println("Agregando participante externo a evento academico");
            gestorEventos.agregarParticipante("Evento Academico 1", participanteExterno1);
            System.out.println("Participante agregado" + participanteExterno1.getNombre());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());

        }

        System.out.println("=============================================");

        try {
            System.out.println("Agregando participante a evento ");
            gestorEventos.agregarParticipante("Evento Academico 1", participanteInterno1);
            System.out.println("Participante agregado " + participanteInterno1.getNombre());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=============================================");

        // Si el participante ya está registrado en el evento, se lanza una excepcion
        try {
            System.out.println("Agregando participante a evento ya registrado");
            gestorEventos.agregarParticipante("Evento Academico 1", participanteInterno1);
            System.out.println("Participante agregado" + participanteInterno1.getNombre());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=============================================");

        // Si el participante ya está inscrito en otro evento, se lanza una excepcion
        try {
            System.out.println("Agregando participante a evento que sucede el mismo dia");
            gestorEventos.agregarParticipante("Evento Externo 1", participanteInterno1);
            System.out.println("Participante agregado" + participanteInterno1.getNombre());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=============================================");

        // Si el evento ya alcanzó su capacidad máxima, se lanza una excepcion
        try {
            System.out.println("Agregando participante a evento académico 1, que ya alcanzó su capacidad máxima");
            gestorEventos.agregarParticipante("Evento Academico 1", participanteInterno2);
            System.out.println("Participante agregado " + participanteInterno2.getNombre());
            gestorEventos.agregarParticipante("Evento Academico 1", participanteInterno3);
            System.out.println("Participante agregado " + participanteInterno3.getNombre());

            //Se lanza una excepcion porque el evento ya alcanzó su capacidad máxima, que es 3
            gestorEventos.agregarParticipante("Evento Academico 1", participanteInterno4);
            System.out.println("Participante agregado " + participanteInterno4.getNombre());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("=============================================");

        //Cálculo de costo de eventos
        System.out.println("Calculando costo total del evento academico 1");
        System.out.println("Agregando 3 participantes al evento academico 1, 1 de ellos es de la misma facultad del evento");
        System.out.println("Costo total del evento academico 1: " + eventoAcademico1.calcularCosto());

        System.out.println("=============================================");

        //Agregar participantes a eventos
        System.out.println("Agregando participantes a evento externo 1");
        System.out.println("Agregando 3 participantes a evento externo 1, 2 internos y 1 externo que hace parte de la entidad patrocinadora");

        gestorEventos.agregarParticipante("Evento Externo 1", participanteInterno4);
        gestorEventos.agregarParticipante("Evento Externo 1", participanteExterno1);
        gestorEventos.agregarParticipante("Evento Externo 1", participanteExterno2);

        //De acuerdo al enunciado, si el participante en un evento externo hace parte de la entidad patrocinadora,
        // se le hace un descuento del 50%. Por lo tanto, el costo total del evento externo 1 sería de 10000 + 10000 + 5000 = 25000

        System.out.println("Costo total del evento externo 1: " + eventoExterno1.calcularCosto());

        System.out.println("=============================================");

        //Agregando participantes al resto de eventos

        gestorEventos.agregarParticipante("Evento Academico 2", participanteInterno1);
        gestorEventos.agregarParticipante("Evento Academico 2", participanteInterno2);

        gestorEventos.agregarParticipante("Evento Academico 3", participanteInterno3);

        gestorEventos.agregarParticipante("Evento Academico 4", participanteInterno1);
        gestorEventos.agregarParticipante("Evento Academico 4", participanteInterno2);
        gestorEventos.agregarParticipante("Evento Academico 4", participanteInterno3);
        gestorEventos.agregarParticipante("Evento Academico 4", participanteInterno4);

        gestorEventos.agregarParticipante("Evento Academico 5", participanteInterno1);
        gestorEventos.agregarParticipante("Evento Academico 5", participanteInterno2);
        gestorEventos.agregarParticipante("Evento Academico 5", participanteInterno3);
        gestorEventos.agregarParticipante("Evento Academico 5", participanteInterno4);
        gestorEventos.agregarParticipante("Evento Academico 5", participanteInterno5);


        gestorEventos.agregarParticipante("Evento Externo 2", participanteExterno1);
        gestorEventos.agregarParticipante("Evento Externo 2", participanteExterno2);

        gestorEventos.agregarParticipante("Evento Externo 3", participanteExterno3);

        gestorEventos.agregarParticipante("Evento Externo 4", participanteExterno1);
        gestorEventos.agregarParticipante("Evento Externo 4", participanteExterno2);
        gestorEventos.agregarParticipante("Evento Externo 4", participanteExterno3);
        gestorEventos.agregarParticipante("Evento Externo 4", participanteExterno4);

        gestorEventos.agregarParticipante("Evento Externo 5", participanteExterno1);
        gestorEventos.agregarParticipante("Evento Externo 5", participanteExterno2);
        gestorEventos.agregarParticipante("Evento Externo 5", participanteExterno3);
        gestorEventos.agregarParticipante("Evento Externo 5", participanteExterno4);
        gestorEventos.agregarParticipante("Evento Externo 5", participanteExterno5);

        //Serialización
        System.out.println("Serializando eventos");
        try {
            gestorEventos.serializar("src/main/resources/eventos.ser");
            System.out.println("Eventos serializados");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("=============================================");
        //Deserialización
        System.out.println("Deserializando eventos");
        try {
            gestorEventos.deserializar("src/main/resources/eventos.ser");
            System.out.println("Eventos deserializados:");
            for (Evento evento : gestorEventos.getEventos()) {
                System.out.println(evento.toString());
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("=============================================");

        //Reporte de eventos
        System.out.println("Generando reporte de eventos");
        try {
            gestorEventos.reporteEventos("src/main/resources/reporte.txt");
            System.out.println("Reporte generado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}