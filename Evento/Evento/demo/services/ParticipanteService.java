package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Ensure the Evento class exists in the 'entities' package

import entities.Evento;
import entities.Participante;

public class ParticipanteService {
    private final List<Participante> participantes;
    private int proximoId;

    public ParticipanteService() {
        this.participantes = new ArrayList<>();
        this.proximoId = 1;
    }

    public Participante cadastrarParticipante(String nome, String email, String cpf) {
        Participante participante = new Participante(nome, email, cpf);
        participante.setId(proximoId++);
        participantes.add(participante);
        return participante;
    }

    public Optional<Participante> buscarParticipantePorId(int id) {
        return participantes.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public Optional<Participante> buscarParticipantePorCpf(String cpf) {
        return participantes.stream()
                .filter(p -> p.getCpf().equals(cpf))
                .findFirst();
    }

    public List<Participante> listarParticipantes() {
        return new ArrayList<>(participantes);
    }

    public boolean atualizarParticipante(int id, String nome, String email, String cpf) {
        Optional<Participante> participanteOptional = buscarParticipantePorId(id);
        if (participanteOptional.isPresent()) {
            Participante participante = participanteOptional.get();
            participante.setNome(nome);
            participante.setEmail(email);
            participante.setCpf(cpf);
            return true;
        }
        return false;
    }

    public boolean removerParticipante(int id) {
        Optional<Participante> participanteOptional = buscarParticipantePorId(id);
        if (participanteOptional.isPresent()) {
            Participante participante = participanteOptional.get();
            return participantes.remove(participante);
        }
        return false;
    }

    public String emitirCertificado(int participanteId, int eventoId, EventoService eventoService) {
        Optional<Participante> participanteOptional = buscarParticipantePorId(participanteId);
        Optional<Evento> eventoOptional = eventoService.buscarEventoPorId(eventoId);
        
        if (participanteOptional.isPresent() && eventoOptional.isPresent()) {
            Participante participante = participanteOptional.get();
            Evento evento = eventoOptional.get();
            return participante.emitirCertificado(evento);
        }
        return null;
    }
}