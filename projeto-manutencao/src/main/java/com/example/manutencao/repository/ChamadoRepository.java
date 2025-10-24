package com.example.manutencao.repository;

import com.example.manutencao.model.Chamado;
import com.example.manutencao.model.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    long countBySituacaoAndDataEncerramentoBetween(Situacao situacao, LocalDateTime start, LocalDateTime end);

    List<Chamado> findByDataAberturaBetweenOrderByDataAbertura(LocalDateTime start, LocalDateTime end);

    long countByDataAberturaBetween(LocalDateTime start, LocalDateTime end);
}
