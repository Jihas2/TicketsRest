package com.example.manutencao.service;

import com.example.manutencao.model.Colaborador;
import com.example.manutencao.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
    private final ColaboradorRepository repo;

    public ColaboradorService(ColaboradorRepository repo) {
        this.repo = repo;
    }

    public Colaborador criar(Colaborador c) {
        return repo.save(c);
    }

    public List<Colaborador> listar() {
        return repo.findAll();
    }

    public Optional<Colaborador> buscar(Long id) {
        return repo.findById(id);
    }

    public Colaborador atualizar(Long id, Colaborador c) {
        c.setId(id);
        return repo.save(c);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}
