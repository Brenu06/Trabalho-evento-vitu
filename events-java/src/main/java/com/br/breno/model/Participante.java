package com.br.breno.model;

import java.util.ArrayList;
import java.util.List;

public class Participante {
  private Integer id;
  private String nome;
  private String email;
  private String cpf;
  private final List<Evento> eventosInscritos;

  public Participante(String nome, String email, String cpf) {
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.eventosInscritos = new ArrayList<>();
  }

  // Getters e Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public List<Evento> getEventosInscritos() {
    return eventosInscritos;
  }

  public boolean inscreverEmEvento(Evento evento) {
    if (!eventosInscritos.contains(evento)) {
      eventosInscritos.add(evento);
      return true;
    }
    return false;
  }

  public boolean cancelarInscricao(Evento evento) {
    return eventosInscritos.remove(evento);
  }

  public String emitirCertificado(Evento evento) {
    if (eventosInscritos.contains(evento)) {
      return "Certificado para " + nome + " no evento " + evento.getNome() +
          " realizado em " + evento.getData() + " no local " + evento.getLocal();
    }
    return null;
  }

  @Override
  public String toString() {
    return "Participante{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}