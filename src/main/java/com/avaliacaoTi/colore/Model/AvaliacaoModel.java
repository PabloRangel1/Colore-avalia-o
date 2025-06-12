package com.avaliacaoTi.colore.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_avaliacao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvaliacaoModel {
    // Tabela Avaliacao, geração de Identificador automático
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nota")
    private int nota;

    @Column(name = "data")
    private LocalDate dataAvaliacao = LocalDate.now();


}
