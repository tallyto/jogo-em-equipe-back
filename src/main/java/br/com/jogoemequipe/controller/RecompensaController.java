package br.com.jogoemequipe.controller;

import br.com.jogoemequipe.model.Recompensa;
import br.com.jogoemequipe.repository.RecompensaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recompensas")
class RecompensaController {
    private final RecompensaRepository recompensaRepository;

    public RecompensaController(RecompensaRepository recompensaRepository) {
        this.recompensaRepository = recompensaRepository;
    }

    @GetMapping
    public List<Recompensa> listarRecompensas() {
        return recompensaRepository.findAll();
    }

    @PostMapping
    public Recompensa criarRecompensa(@RequestBody Recompensa recompensa) {
        return recompensaRepository.save(recompensa);
    }
}