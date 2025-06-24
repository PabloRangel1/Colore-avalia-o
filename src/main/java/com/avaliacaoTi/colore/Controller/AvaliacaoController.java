package com.avaliacaoTi.colore.Controller;

import com.avaliacaoTi.colore.Dto.AvaliacaoDTO;
import com.avaliacaoTi.colore.Mapper.AvaliacaoMapper;
import com.avaliacaoTi.colore.Model.AvaliacaoModel;
import com.avaliacaoTi.colore.Repository.AvaliacaoRepository;
import com.avaliacaoTi.colore.Service.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoRepository avRep;
    private final AvaliacaoMapper avMap;
    private final AvaliacaoService avSer;

    public AvaliacaoController(AvaliacaoRepository avRep, AvaliacaoMapper avMap, AvaliacaoService avSer) {
        this.avRep = avRep;
        this.avMap = avMap;
        this.avSer = avSer;
    }

    // Cria uma nova avaliação
    @PostMapping("/criar")
    public ResponseEntity<AvaliacaoDTO> criarAvaliacao(@RequestBody AvaliacaoDTO nota) {
        // Validação: nota deve estar entre 1 e 3
        if (nota.getNota() < 1 || nota.getNota() > 3) {
            return ResponseEntity.badRequest().body(null);
        }

        AvaliacaoModel avModel = avMap.toModel(nota);
        avModel.setNota(nota.getNota());
        avModel.setDataAvaliacao(LocalDate.now());

        avModel = avRep.save(avModel);
        return ResponseEntity.ok(avMap.toDTO(avModel));
    }

    // Lista todas as avaliações salvas
    @GetMapping("/media")
    public ResponseEntity<List<AvaliacaoDTO>> listarMedia() {
        List<AvaliacaoDTO> listaModel = avSer.listarAvaliacoes();
        return ResponseEntity.ok(listaModel);
    }

    // Calcula a média no período e retorna a classificação textual
    @GetMapping("/periodo")
    public ResponseEntity<String> media(@RequestParam String inicio, @RequestParam String fim) {
        LocalDate i = LocalDate.parse(inicio);
        LocalDate f = LocalDate.parse(fim);
        double media = avSer.calcularMediaPeriodo(i, f);

        String resultado;
        if (media >= 2.8) {
            resultado = "Excelente";
        } else if (media >= 2.3) {
            resultado = "Bom";
        } else if (media >= 1.8) {
            resultado = "Regular";
        } else {
            resultado = "Ruim";
        }

        return ResponseEntity.ok("Média: " + media + " - " + resultado);
    }
}
