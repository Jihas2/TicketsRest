package com.example.manutencao.controller;

import com.example.manutencao.model.Colaborador;
import com.example.manutencao.service.ColaboradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Colaborador> criar(@RequestBody Colaborador c) {
        return ResponseEntity.ok(service.criar(c));
    }

    @GetMapping
    public ResponseEntity<List<Colaborador>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> buscar(@PathVariable Long id) {
        return service.buscar(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> atualizar(@PathVariable Long id, @RequestBody Colaborador c) {
        return ResponseEntity.ok(service.atualizar(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
