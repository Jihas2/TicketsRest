package com.example.manutencao.service;

import com.example.manutencao.model.*;
import com.example.manutencao.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    private final ChamadoRepository chamadoRepo;
    private final ColaboradorRepository colaboradorRepo;

    public ChamadoService(ChamadoRepository chamadoRepo, ColaboradorRepository colaboradorRepo) {
        this.chamadoRepo = chamadoRepo;
        this.colaboradorRepo = colaboradorRepo;
    }

    public Chamado criar(Chamado c) {
        if (c.getColaborador() == null) c.setSituacao(Situacao.SEM_ALOCACAO);
        if (c.getDataAbertura() == null) c.setDataAbertura(LocalDateTime.now());
        return chamadoRepo.save(c);
    }

    public List<Chamado> listar() {
        return chamadoRepo.findAll();
    }

    public Optional<Chamado> buscar(Long id) {
        return chamadoRepo.findById(id);
    }

    public Chamado atualizar(Long id, Chamado c) {
        c.setId(id);
        return chamadoRepo.save(c);
    }

    public void deletar(Long id) {
        chamadoRepo.deleteById(id);
    }

    @Transactional
    public Chamado alocar(Long chamadoId, Long colaboradorId) {
        Chamado ch = chamadoRepo.findById(chamadoId).orElseThrow();
        Colaborador col = colaboradorRepo.findById(colaboradorId).orElseThrow();
        ch.setColaborador(col);
        ch.setSituacao(Situacao.EM_ESPERA);
        return chamadoRepo.save(ch);
    }

    @Transactional
    public Chamado encerrar(Long chamadoId) {
        Chamado ch = chamadoRepo.findById(chamadoId).orElseThrow();
        ch.setSituacao(Situacao.ATENDIDO);
        ch.setDataEncerramento(LocalDateTime.now());
        return chamadoRepo.save(ch);
    }

    public long atendidosNoMes(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        LocalDateTime s = start.atStartOfDay();
        LocalDateTime e = end.atTime(LocalTime.MAX);
        return chamadoRepo.countBySituacaoAndDataEncerramentoBetween(Situacao.ATENDIDO, s, e);
    }

    public List<Chamado> listarPorAno(int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        return chamadoRepo.findByDataAberturaBetweenOrderByDataAbertura(start.atStartOfDay(), end.atTime(LocalTime.MAX));
    }

    public double coeficienteResolvidos(int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        long abertos = chamadoRepo.countByDataAberturaBetween(start.atStartOfDay(), end.atTime(LocalTime.MAX));
        long atendidos = chamadoRepo.countBySituacaoAndDataEncerramentoBetween(Situacao.ATENDIDO, start.atStartOfDay(), end.atTime(LocalTime.MAX));
        if (abertos == 0) return 0.0;
        return ((double) atendidos) / abertos;
    }
}
