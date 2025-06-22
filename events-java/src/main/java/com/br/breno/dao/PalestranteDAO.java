package com.br.breno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.br.breno.model.Palestrante;

public class PalestranteDAO {

  public void create(Palestrante palestrante) {
    String sql = "INSERT INTO palestrante (nome, curriculo, area_atuacao) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, palestrante.getNome());
      pstmt.setString(2, palestrante.getCurriculo());
      pstmt.setString(3, palestrante.getAreaAtuacao());
      pstmt.executeUpdate();

      try (ResultSet rs = pstmt.getGeneratedKeys()) {
        if (rs.next()) {
          palestrante.setId(rs.getInt(1));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Palestrante findById(int id) {
    String sql = "SELECT * FROM palestrante WHERE id = ?";
    Palestrante palestrante = null;
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        palestrante = new Palestrante(
          rs.getString("nome"),
          rs.getString("curriculo"),
          rs.getString("area_atuacao")
        );
        palestrante.setId(rs.getInt("id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return palestrante;
  }

  public List<Palestrante> findAll() {
    List<Palestrante> palestrantes = new ArrayList<>();
    String sql = "SELECT * FROM palestrante";
    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        Palestrante palestrante = new Palestrante(
          rs.getString("nome"),
          rs.getString("curriculo"),
          rs.getString("area_atuacao")
        );
        palestrante.setId(rs.getInt("id"));
        palestrantes.add(palestrante);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return palestrantes;
  }

  public void update(Palestrante palestrante) {
    String sql = "UPDATE palestrante SET nome = ?, curriculo = ?, area_atuacao = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, palestrante.getNome());
      pstmt.setString(2, palestrante.getCurriculo());
      pstmt.setString(3, palestrante.getAreaAtuacao());
      pstmt.setInt(4, palestrante.getId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void delete(int id) {
    String sql = "DELETE FROM palestrante WHERE id = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void associarPalestranteEvento(int palestranteId, int eventoId) {
    String sql = "INSERT INTO evento_palestrante (evento_id, palestrante_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, eventoId);
        pstmt.setInt(2, palestranteId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}