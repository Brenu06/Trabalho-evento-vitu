package com.br.breno.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.br.breno.model.Evento;

public class EventoDAO {

  public void create(Evento evento) {
    String sql = "INSERT INTO evento (nome, descricao, data, local, capacidade) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, evento.getNome());
      pstmt.setString(2, evento.getDescricao());
      pstmt.setDate(3, Date.valueOf(evento.getData()));
      pstmt.setString(4, evento.getLocal());
      pstmt.setInt(5, evento.getCapacidade());
      pstmt.executeUpdate();

      try (ResultSet rs = pstmt.getGeneratedKeys()) {
        if (rs.next()) {
          evento.setId(rs.getInt(1));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Evento findById(int id) {
    String sql = "SELECT * FROM evento WHERE id = ?";
    Evento evento = null;
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        evento = new Evento(
          rs.getString("nome"),
          rs.getString("descricao"),
          rs.getDate("data").toLocalDate(),
          rs.getString("local"),
          rs.getInt("capacidade")
        );
        evento.setId(rs.getInt("id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return evento;
  }

  public List<Evento> findAll() {
    List<Evento> eventos = new ArrayList<>();
    String sql = "SELECT * FROM evento";
    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        Evento evento = new Evento(
          rs.getString("nome"),
          rs.getString("descricao"),
          rs.getDate("data").toLocalDate(),
          rs.getString("local"),
          rs.getInt("capacidade")
        );
        evento.setId(rs.getInt("id"));
        eventos.add(evento);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return eventos;
  }

  public void update(Evento evento) {
    String sql = "UPDATE evento SET nome = ?, descricao = ?, data = ?, local = ?, capacidade = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, evento.getNome());
      pstmt.setString(2, evento.getDescricao());
      pstmt.setDate(3, Date.valueOf(evento.getData()));
      pstmt.setString(4, evento.getLocal());
      pstmt.setInt(5, evento.getCapacidade());
      pstmt.setInt(6, evento.getId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(int id) {
    String deleteInscricoes = "DELETE FROM inscricao WHERE evento_id = ?";
    String deleteEventoPalestrante = "DELETE FROM evento_palestrante WHERE evento_id = ?";
    String deleteEvento = "DELETE FROM evento WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection()) {
        // Exclui inscrições
        try (PreparedStatement pstmt = conn.prepareStatement(deleteInscricoes)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        // Exclui palestrantes associados
        try (PreparedStatement pstmt = conn.prepareStatement(deleteEventoPalestrante)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        // Exclui o evento
        try (PreparedStatement pstmt = conn.prepareStatement(deleteEvento)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
  }
}