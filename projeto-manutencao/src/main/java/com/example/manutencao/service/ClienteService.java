package com.example.manutencao.service;

import com.example.manutencao.model.Cliente;
import com.example.manutencao.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public Cliente criar(Cliente c) {
        return repo.save(c);
    }

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Optional<Cliente> buscar(Long id) {
        return repo.findById(id);
    }

    public Cliente atualizar(Long id, Cliente c) {
        c.setId(id);
        return repo.save(c);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}
