package br.com.jogoemequipe.controller;


import br.com.jogoemequipe.model.Tarefa;
import br.com.jogoemequipe.model.Usuario;
import br.com.jogoemequipe.service.TarefaService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluirTarefa(@PathVariable UUID id) {
        try {
            var usuario = getUsuario();

            Tarefa tarefaConcluida = tarefaService.concluirTarefa(id, usuario.getId());
            return ResponseEntity.ok(tarefaConcluida);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    private static Usuario getUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Usuario) authentication.getPrincipal();
        return usuario;
    }
    // Outros endpoints do controller (criar, listar, etc.) podem vir aqui
}