package com.br.breno.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.br.breno.model.Participante;

class ParticipanteDAOTest {
    private ParticipanteDAO participanteDAO;

    @BeforeEach
    void setUp() {
        participanteDAO = new ParticipanteDAO();
    }

    @Test
    void testCreateAndFindById() {
        Participante p = new Participante("Teste", "teste@email.com", "12345678901");
        participanteDAO.create(p);
        assertNotNull(p.getId());

        Participante found = participanteDAO.findById(p.getId());
        assertNotNull(found);
        assertEquals("Teste", found.getNome());
    }

    @Test
    void testFindAll() {
        List<Participante> participantes = participanteDAO.findAll();
        assertNotNull(participantes);
    }

    @Test
    void testUpdate() {
        Participante p = new Participante("Update", "update@email.com", "12345678901");
        participanteDAO.create(p);
        p.setNome("Atualizado");
        participanteDAO.update(p);

        Participante updated = participanteDAO.findById(p.getId());
        assertEquals("Atualizado", updated.getNome());
    }

    @Test
    void testDelete() {
        Participante p = new Participante("Delete", "delete@email.com", "12345678901");
        participanteDAO.create(p);
        int id = p.getId();
        participanteDAO.delete(id);
        assertNull(participanteDAO.findById(id));
    }
}
