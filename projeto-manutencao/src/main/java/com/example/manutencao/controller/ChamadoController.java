package com.example.manutencao.controller;

import com.example.manutencao.model.Chamado;
import com.example.manutencao.service.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Chamado> criar(@RequestBody Chamado c) {
        return ResponseEntity.ok(service.criar(c));
    }

    @GetMapping
    public ResponseEntity<List<Chamado>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscar(@PathVariable Long id) {
        return service.buscar(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chamado> atualizar(@PathVariable Long id, @RequestBody Chamado c) {
        return ResponseEntity.ok(service.atualizar(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/alocar")
    public ResponseEntity<Chamado> alocar(@PathVariable Long id, @RequestBody Map<String, Long> body) {
        Long colaboradorId = body.get("colaboradorId");
        return ResponseEntity.ok(service.alocar(id, colaboradorId));
    }

    @PutMapping("/{id}/encerrar")
    public ResponseEntity<Chamado> encerrar(@PathVariable Long id) {
        return ResponseEntity.ok(service.encerrar(id));
    }

    @GetMapping("/relatorios/atendidos-no-mes")
    public ResponseEntity<Long> atendidosNoMes(@RequestParam int ano, @RequestParam int mes) {
        return ResponseEntity.ok(service.atendidosNoMes(ano, mes));
    }

    @GetMapping("/relatorios/listar-ano")
    public ResponseEntity<List<Chamado>> listarPorAno(@RequestParam int ano) {
        return ResponseEntity.ok(service.listarPorAno(ano));
    }

    @GetMapping("/relatorios/coefi")
    public ResponseEntity<Double> coeficiente(@RequestParam int ano) {
        return ResponseEntity.ok(service.coeficienteResolvidos(ano));
    }
}
