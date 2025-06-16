package br.com.jogoemequipe.controller;

import br.com.jogoemequipe.dto.DesafioDTO;
import br.com.jogoemequipe.dto.TarefaDTO;
import br.com.jogoemequipe.model.Desafio;
import br.com.jogoemequipe.model.Tarefa;
import br.com.jogoemequipe.model.Usuario;
import br.com.jogoemequipe.service.DesafioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/desafios")
@RequiredArgsConstructor
public class DesafioController {

    private final DesafioService desafioService;

    @GetMapping
    public List<Desafio> listarDesafios() {
        var usuarioId = getUsuario().getId();
        return desafioService.listarDesafios(usuarioId);
    }

    @PostMapping
    public ResponseEntity<Desafio> criarDesafio(@RequestBody DesafioDTO dto) {
        Usuario usuario = getUsuario();
        Desafio desafio = desafioService.criarDesafio(dto, usuario.getId());
        return ResponseEntity.ok(desafio);
    }

    @PostMapping("/{desafioId}/tarefas")
    public ResponseEntity<Tarefa> criarTarefa(@PathVariable UUID desafioId, @RequestBody TarefaDTO dto) {
        Usuario usuario = getUsuario();
        Tarefa tarefa = desafioService.criarTarefa(desafioId, dto, usuario.getId());
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping("/{desafioId}/tarefas")
    public ResponseEntity<List<Tarefa>> listarTarefasDoDesafio(@PathVariable UUID desafioId) {
        List<Tarefa> tarefas = desafioService.listarTarefasDoDesafio(desafioId);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{desafioId}")
    public ResponseEntity<Desafio> acessarDesafio(@PathVariable UUID desafioId) {
        UUID usuarioId = getUsuario().getId(); // Recupera o ID do usuário logado
        Desafio desafio = desafioService.buscarDesafioPorId(desafioId, usuarioId);  // Busca o desafio para o usuário logado
        return ResponseEntity.ok(desafio);  // Retorna o desafio encontrado
    }

    private static Usuario getUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Usuario) authentication.getPrincipal();
        return usuario;
    }

    @PostMapping("/{desafioId}/usuarios/{usuarioId}")
    public ResponseEntity<Desafio> adicionarUsuario(@PathVariable UUID desafioId, @PathVariable UUID usuarioId) {
        Desafio desafio = desafioService.adicionarUsuarioAoDesafio(desafioId, usuarioId);
        return ResponseEntity.ok(desafio);
    }

    @GetMapping("/{desafioId}/participantes")
    public ResponseEntity<List<Usuario>> listarParticipantes(@PathVariable UUID desafioId) {
        Desafio desafio = desafioService.buscarDesafioPorId(desafioId, getUsuario().getId());
        return ResponseEntity.ok(List.copyOf(desafio.getParticipantes()));
    }

    public static class EmailRequest {
        public String email;
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    @PostMapping("/{desafioId}/participante")
    public ResponseEntity<Desafio> adicionarParticipantePorEmail(@PathVariable UUID desafioId, @RequestBody EmailRequest request) {
        Desafio desafio = desafioService.adicionarParticipantePorEmail(desafioId, request.getEmail());
        return ResponseEntity.ok(desafio);
    }
}
