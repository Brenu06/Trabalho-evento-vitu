package com.br.breno.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.br.breno.model.Evento;

class EventoDAOTest {
    private EventoDAO eventoDAO;

    @BeforeEach
    void setUp() {
        eventoDAO = new EventoDAO();
    }

    @Test
    void testCreateAndFindById() {
        Evento evento = new Evento("Teste", "Descricao", LocalDate.now(), "Local", 100);
        eventoDAO.create(evento);
        assertNotNull(evento.getId());

        Evento found = eventoDAO.findById(evento.getId());
        assertNotNull(found);
        assertEquals("Teste", found.getNome());
    }

    @Test
    void testFindAll() {
        List<Evento> eventos = eventoDAO.findAll();
        assertNotNull(eventos);
    }

    @Test
    void testUpdate() {
        Evento evento = new Evento("Update", "Desc", LocalDate.now(), "Local", 50);
        eventoDAO.create(evento);
        evento.setNome("Atualizado");
        eventoDAO.update(evento);

        Evento updated = eventoDAO.findById(evento.getId());
        assertEquals("Atualizado", updated.getNome());
    }

    @Test
    void testDelete() {
        Evento evento = new Evento("Delete", "Desc", LocalDate.now(), "Local", 10);
        eventoDAO.create(evento);
        int id = evento.getId();
        eventoDAO.delete(id);
        assertNull(eventoDAO.findById(id));
    }
}
