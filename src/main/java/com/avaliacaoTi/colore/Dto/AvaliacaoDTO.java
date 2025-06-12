package com.avaliacaoTi.colore.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {
    // Este é meu DTO ( Data-Transfer-Object)
    private Long id;
    private int nota;
    private LocalDate dataAvaliacao;
}
