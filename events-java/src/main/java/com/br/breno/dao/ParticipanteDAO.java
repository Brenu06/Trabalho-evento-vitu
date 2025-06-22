package com.br.breno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.br.breno.model.Participante;

public class ParticipanteDAO {

  public void create(Participante participante) {
    String sql = "INSERT INTO participante (nome, email, cpf) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, participante.getNome());
      pstmt.setString(2, participante.getEmail());
      pstmt.setString(3, participante.getCpf());
      pstmt.executeUpdate();

      try (ResultSet rs = pstmt.getGeneratedKeys()) {
        if (rs.next()) {
          participante.setId(rs.getInt(1));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Participante findById(int id) {
    String sql = "SELECT * FROM participante WHERE id = ?";
    Participante participante = null;
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        participante = new Participante(
          rs.getString("nome"),
          rs.getString("email"),
          rs.getString("cpf")
        );
        participante.setId(rs.getInt("id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return participante;
  }

  public List<Participante> findAll() {
    List<Participante> participantes = new ArrayList<>();
    String sql = "SELECT * FROM participante";
    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        Participante participante = new Participante(
          rs.getString("nome"),
          rs.getString("email"),
          rs.getString("cpf")
        );
        participante.setId(rs.getInt("id"));
        participantes.add(participante);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return participantes;
  }

  public void update(Participante participante) {
    String sql = "UPDATE participante SET nome = ?, email = ?, cpf = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, participante.getNome());
      pstmt.setString(2, participante.getEmail());
      pstmt.setString(3, participante.getCpf());
      pstmt.setInt(4, participante.getId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(int id) {
    String sql = "DELETE FROM participante WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void associarParticipanteEvento(int participanteId, int eventoId) {
    String sql = "INSERT INTO inscricao (evento_id, participante_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, eventoId);
        pstmt.setInt(2, participanteId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}