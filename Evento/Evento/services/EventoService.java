package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.Evento;
import entities.Palestrante;
import entities.Participante;

public class EventoService {
    private final List<Evento> eventos;
    private int proximoId;

    public EventoService() {
        this.eventos = new ArrayList<>();
        this.proximoId = 1;
    }

    
    public Evento criarEvento(String nome, String descricao, LocalDate data, String local, int capacidade) {
        if (nome == null || nome.isBlank() || data == null || local == null || local.isBlank() || capacidade <= 0) {
            throw new IllegalArgumentException("Dados do evento inválidos.");
        }
        Evento evento = new Evento(nome, descricao, data, local, capacidade);
        evento.setId(proximoId++);
        eventos.add(evento);
        return evento;
    }



     
    public Optional<Evento> buscarEventoPorId(int id) {
        return eventos.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    
    public List<Evento> listarEventos() {
        return new ArrayList<>(eventos);
    }

   
   
     
    public List<Evento> listarEventosFuturos() {
        LocalDate hoje = LocalDate.now();
        return eventos.stream()
                .filter(e -> e.getData().isAfter(hoje) || e.getData().isEqual(hoje))
                .toList(); 
    }

    
    public boolean atualizarEvento(int id, String nome, String descricao, LocalDate data, String local, int capacidade) {
        Optional<Evento> eventoOptional = buscarEventoPorId(id);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            if (nome != null && !nome.isBlank()) evento.setNome(nome);
            if (descricao != null) evento.setDescricao(descricao);
            if (data != null) evento.setData(data);
            if (local != null && !local.isBlank()) evento.setLocal(local);
            if (capacidade > 0) evento.setCapacidade(capacidade);
            return true;
        }
        return false;
    }

    
    public boolean cancelarEvento(int id) {
        Optional<Evento> eventoOptional = buscarEventoPorId(id);
        return eventoOptional.map(eventos::remove).orElse(false);
    }

    
    public boolean adicionarPalestranteAoEvento(int eventoId, Palestrante palestrante) {
        if (palestrante == null) return false;
        Optional<Evento> eventoOptional = buscarEventoPorId(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            boolean adicionado = evento.adicionarPalestrante(palestrante);
            if (adicionado) {
                palestrante.adicionarEvento(evento);
            }
            return adicionado;
        }
        return false;
    }

    
    public boolean removerPalestranteDoEvento(int eventoId, Palestrante palestrante) {
        if (palestrante == null) return false;
        Optional<Evento> eventoOptional = buscarEventoPorId(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            boolean removido = evento.removerPalestrante(palestrante);
            if (removido) {
                palestrante.removerEvento(evento);
            }
            return removido;
        }
        return false;
    }

    
    public boolean inscreverParticipante(int eventoId, Participante participante) {
        if (participante == null) return false;
        Optional<Evento> eventoOptional = buscarEventoPorId(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            boolean inscrito = evento.adicionarParticipante(participante);
            if (inscrito) {
                participante.inscreverEmEvento(evento);
            }
            return inscrito;
        }
        return false;
    }

    
    public boolean cancelarInscricao(int eventoId, Participante participante) {
        if (participante == null) return false;
        Optional<Evento> eventoOptional = buscarEventoPorId(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            boolean cancelado = evento.removerParticipante(participante);
            if (cancelado) {
                participante.cancelarInscricao(evento);
            }
            return cancelado;
        }
        return false;
    }
}