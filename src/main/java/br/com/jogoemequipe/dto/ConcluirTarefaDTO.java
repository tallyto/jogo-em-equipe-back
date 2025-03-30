package br.com.jogoemequipe.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ConcluirTarefaDTO {
    private UUID usuarioId; // ID do usuário que está concluindo a tarefa
}