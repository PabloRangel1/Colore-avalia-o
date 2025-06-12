package com.avaliacaoTi.colore.Repository;
import com.avaliacaoTi.colore.Model.AvaliacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

//Conexão com Banco de Dados via JPA
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoModel, Long> {
    // Filtra por periodo
    // JPA: interpreta esse metodo
    // findBy: prefixo de buscar dados
    // Apartir: da Model é requisitado dataAvaliacao
    // beetwen: Prefixo de Intervalo
    List<AvaliacaoModel> findByDataAvaliacaoBetween(LocalDate inicio, LocalDate fim);
}
