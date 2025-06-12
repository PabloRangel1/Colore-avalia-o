package com.avaliacaoTi.colore.Mapper;

import com.avaliacaoTi.colore.Dto.AvaliacaoDTO;
import com.avaliacaoTi.colore.Model.AvaliacaoModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {
    AvaliacaoModel toModel(AvaliacaoDTO dto);
    AvaliacaoDTO toDTO(AvaliacaoModel model);
}
