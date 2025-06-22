package com.br.breno.model;

import java.util.ArrayList;
import java.util.List;

public class Palestrante {
  private Integer id;
  private String nome;
  private String curriculo;
  private String areaAtuacao;
  private final List<Evento> eventos;

  public Palestrante(String nome, String curriculo, String areaAtuacao) {
    this.nome = nome;
    this.curriculo = curriculo;
    this.areaAtuacao = areaAtuacao;
    this.eventos = new ArrayList<>();
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

  public String getCurriculo() {
    return curriculo;
  }

  public void setCurriculo(String curriculo) {
    this.curriculo = curriculo;
  }

  public String getAreaAtuacao() {
    return areaAtuacao;
  }

  public void setAreaAtuacao(String areaAtuacao) {
    this.areaAtuacao = areaAtuacao;
  }

  public List<Evento> getEventos() {
    return eventos;
  }

  public boolean adicionarEvento(Evento evento) {
    if (!eventos.contains(evento)) {
      eventos.add(evento);
      return true;
    }
    return false;
  }

  public boolean removerEvento(Evento evento) {
    return eventos.remove(evento);
  }

  @Override
  public String toString() {
    return "Palestrante{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", areaAtuacao='" + areaAtuacao + '\'' +
        '}';
  }
}