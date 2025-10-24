package com.example.manutencao.service;

import com.example.manutencao.model.Endereco;
import com.example.manutencao.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository repo;

    public EnderecoService(EnderecoRepository repo) {
        this.repo = repo;
    }

    public Endereco criar(Endereco e) {
        return repo.save(e);
    }

    public List<Endereco> listar() {
        return repo.findAll();
    }

    public Optional<Endereco> buscar(Long id) {
        return repo.findById(id);
    }

    public Endereco atualizar(Long id, Endereco e) {
        e.setId(id);
        return repo.save(e);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}
