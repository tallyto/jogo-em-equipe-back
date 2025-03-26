package br.com.jogoemequipe.controller;


import br.com.jogoemequipe.model.HistoricoPontos;
import br.com.jogoemequipe.repository.HistoricoPontosRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico-pontos")
class HistoricoPontosController {
    private final HistoricoPontosRepository historicoPontosRepository;

    public HistoricoPontosController(HistoricoPontosRepository historicoPontosRepository) {
        this.historicoPontosRepository = historicoPontosRepository;
    }

    @GetMapping
    public List<HistoricoPontos> listarHistorico() {
        return historicoPontosRepository.findAll();
    }

    @PostMapping
    public HistoricoPontos registrarPontos(@RequestBody HistoricoPontos historicoPontos) {
        return historicoPontosRepository.save(historicoPontos);
    }
}
