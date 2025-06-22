package com.br.breno.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.br.breno.model.Palestrante;

class PalestranteDAOTest {
    private PalestranteDAO palestranteDAO;

    @BeforeEach
    void setUp() {
        palestranteDAO = new PalestranteDAO();
    }

    @Test
    void testCreateAndFindById() {
        Palestrante p = new Palestrante("Teste", "Curriculo", "Area");
        palestranteDAO.create(p);
        assertNotNull(p.getId());

        Palestrante found = palestranteDAO.findById(p.getId());
        assertNotNull(found);
        assertEquals("Teste", found.getNome());
    }

    @Test
    void testFindAll() {
        List<Palestrante> palestrantes = palestranteDAO.findAll();
        assertNotNull(palestrantes);
    }

    @Test
    void testUpdate() {
        Palestrante p = new Palestrante("Update", "Curriculo", "Area");
        palestranteDAO.create(p);
        p.setNome("Atualizado");
        palestranteDAO.update(p);

        Palestrante updated = palestranteDAO.findById(p.getId());
        assertEquals("Atualizado", updated.getNome());
    }

    @Test
    void testDelete() {
        Palestrante p = new Palestrante("Delete", "Curriculo", "Area");
        palestranteDAO.create(p);
        int id = p.getId();
        palestranteDAO.delete(id);
        assertNull(palestranteDAO.findById(id));
    }
}
