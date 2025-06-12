package com.avaliacaoTi.colore.Service;

import com.avaliacaoTi.colore.Dto.AvaliacaoDTO;
import com.avaliacaoTi.colore.Mapper.AvaliacaoMapper;
import com.avaliacaoTi.colore.Model.AvaliacaoModel;
import com.avaliacaoTi.colore.Repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    AvaliacaoRepository avRep;
    AvaliacaoMapper avMap;

    public AvaliacaoService(AvaliacaoMapper avMap, AvaliacaoRepository avRep) {
        this.avMap = avMap;
        this.avRep = avRep;
    }

    // Salvar avaliação
    public AvaliacaoDTO criarAvaliacao(AvaliacaoDTO dto){
        AvaliacaoModel model = avMap.toModel(dto); //Conversão para Model
        model.setNota(dto.getNota());
        model.setDataAvaliacao(LocalDate.now());
        AvaliacaoModel salvo = avRep.save(model); // Cria outra variavel para armazenar os dados inseridos
        // Retorna para DTO
        return avMap.toDTO(salvo);
    }

    // Listar todas as avaliações
    public List<AvaliacaoDTO> listarAvaliacoes() {
        List<AvaliacaoModel> listAv = avRep.findAll();
        return listAv.stream()
                .map(avMap::toDTO)
                .collect(Collectors.toList());
    }

    // Filtrar por periodo
    public double calcularMediaPeriodo(LocalDate inicio, LocalDate fim){
        List<AvaliacaoModel> lista = avRep.findByDataAvaliacaoBetween(inicio, fim);
        return lista.stream()
                .mapToInt(AvaliacaoModel::getNota)
                .average()
                .orElse(0.0);
    }

    public void salvarNota(int nota) {
    AvaliacaoModel model = new AvaliacaoModel();
    model.setNota(nota);
    model.setDataAvaliacao(LocalDate.now());
    avRep.save(model);
}

}
