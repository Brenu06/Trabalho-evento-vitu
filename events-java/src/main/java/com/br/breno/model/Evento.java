package com.br.breno.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento {
  private Integer id;
  private String nome;
  private String descricao;
  private LocalDate data;
  private String local;
  private int capacidade;
  private final List<Palestrante> palestrantes;
  private final List<Participante> participantes;

  public Evento(String nome, String descricao, LocalDate data, String local, int capacidade) {
    this.nome = nome;
    this.descricao = descricao;
    this.data = data;
    this.local = local;
    this.capacidade = capacidade;
    this.palestrantes = new ArrayList<>();
    this.participantes = new ArrayList<>();
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

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public String getLocal() {
    return local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public int getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(int capacidade) {
    this.capacidade = capacidade;
  }

  public List<Palestrante> getPalestrantes() {
    return palestrantes;
  }

  public boolean adicionarPalestrante(Palestrante palestrante) {
    if (!palestrantes.contains(palestrante)) {
      palestrantes.add(palestrante);
      return true;
    }
    return false;
  }

  public boolean removerPalestrante(Palestrante palestrante) {
    return palestrantes.remove(palestrante);
  }

  public List<Participante> getParticipantes() {
    return participantes;
  }

  public boolean adicionarParticipante(Participante participante) {
    if (participantes.size() < capacidade && !participantes.contains(participante)) {
      participantes.add(participante);
      return true;
    }
    return false;
  }

  public boolean removerParticipante(Participante participante) {
    return participantes.remove(participante);
  }

  public boolean isLotado() {
    return participantes.size() >= capacidade;
  }

  @Override
  public String toString() {
    return "Evento{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", data=" + data +
        ", local='" + local + '\'' +
        ", capacidade=" + capacidade +
        ", vagasDisponiveis=" + (capacidade - participantes.size()) +
        '}';
  }
}