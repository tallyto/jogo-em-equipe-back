package br.com.jogoemequipe.controller;

import br.com.jogoemequipe.dto.RecompensaDTO;
import br.com.jogoemequipe.model.Recompensa;
import br.com.jogoemequipe.service.RecompensaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recompensas")
@RequiredArgsConstructor
public class RecompensaController {

    private final RecompensaService recompensaService;

    @PostMapping
    public ResponseEntity<Recompensa> criarRecompensa(@Valid @RequestBody RecompensaDTO dto) {
        Recompensa recompensa = recompensaService.criarRecompensa(dto);
        return ResponseEntity.ok(recompensa);
    }

    @GetMapping("/{desafioId}")
    public ResponseEntity<List<Recompensa>> listarRecompensas(@PathVariable UUID desafioId) {
        List<Recompensa> recompensas = recompensaService.listarRecompensas(desafioId);
        return ResponseEntity.ok(recompensas);
    }
}
