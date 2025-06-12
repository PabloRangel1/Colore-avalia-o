package com.avaliacaoTi.colore.ControllerUI;

import com.avaliacaoTi.colore.Service.AvaliacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/ui")
public class AvaliacaoControllerUI {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoControllerUI(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    // Página inicial de avaliação
    @GetMapping("/avaliar")
    public String mostrarPaginaAvaliacao() {
        return "avaliacao"; // templates/avaliacao.html
    }

    // Endpoint para receber a nota via AJAX
    @PostMapping("/avaliar")
    @ResponseBody
    public Map<String, String> salvarAvaliacao(@RequestBody Map<String, Integer> body) {
        Integer nota = body.get("nota");
        if (nota == null || nota < 0 || nota > 10) {
            return Map.of("erro", "Nota inválida. Deve ser entre 0 e 10.");
        }
        avaliacaoService.salvarNota(nota);
        return Map.of("mensagem", "Avaliação registrada com sucesso!");
    }

    @GetMapping("/media-form")
    public String mostrarFormularioDeMedia() {
        return "media-form"; // templates/media-form.html
    }

    // Página com resultado da média por período
    @GetMapping("/media")
    public String mostrarMediaPorPeriodo(@RequestParam String inicio, @RequestParam String fim, Model model) {
        var dataInicio = java.time.LocalDate.parse(inicio);
        var dataFim = java.time.LocalDate.parse(fim);

        double media = avaliacaoService.calcularMediaPeriodo(dataInicio, dataFim);

        String resultado;
        if (media >= 8) {
            resultado = "Excelente 😄";
        } else if (media >= 6) {
            resultado = "Bom 😊";
        } else if (media >= 4) {
            resultado = "Regular 😐";
        } else {
            resultado = "Ruim 😞";
        }

        model.addAttribute("media", media);
        model.addAttribute("classificacao", resultado);
        model.addAttribute("inicio", inicio);
        model.addAttribute("fim", fim);

        return "media"; // templates/media.html
    }
}
