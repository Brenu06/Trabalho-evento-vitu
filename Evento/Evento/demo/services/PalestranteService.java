package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Ensure the Palestrante class exists in the 'entities' package and is correctly imported.
import entities.Palestrante;

public class PalestranteService {
    private final List<Palestrante> palestrantes;
    private int proximoId;

    public PalestranteService() {
        this.palestrantes = new ArrayList<>();
        this.proximoId = 1;
    }

    public Palestrante cadastrarPalestrante(String nome, String curriculo, String areaAtuacao) {
        Palestrante palestrante = new Palestrante(nome, curriculo, areaAtuacao);
        palestrante.setId(proximoId++);
        palestrantes.add(palestrante);
        return palestrante;
    }

    public Optional<Palestrante> buscarPalestrantePorId(int id) {
        return palestrantes.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public List<Palestrante> listarPalestrantes() {
        return new ArrayList<>(palestrantes);
    }

    public boolean atualizarPalestrante(int id, String nome, String curriculo, String areaAtuacao) {
        Optional<Palestrante> palestranteOptional = buscarPalestrantePorId(id);
        if (palestranteOptional.isPresent()) {
            Palestrante palestrante = palestranteOptional.get();
            palestrante.setNome(nome);
            palestrante.setCurriculo(curriculo);
            palestrante.setAreaAtuacao(areaAtuacao);
            return true;
        }
        return false;
    }

    public boolean removerPalestrante(int id) {
        Optional<Palestrante> palestranteOptional = buscarPalestrantePorId(id);
        if (palestranteOptional.isPresent()) {
            Palestrante palestrante = palestranteOptional.get();
            return palestrantes.remove(palestrante);
        }
        return false;
    }
}