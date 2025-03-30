package br.com.jogoemequipe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String descricao;
    private int pontos;

    private String status;

    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;

    @ManyToOne
    @JoinColumn(name = "usuario_criador_id")
    private Usuario usuarioCriador; // Quem cadastrou a tarefa

    @ManyToOne
    @JoinColumn(name = "usuario_concluidor_id")
    private Usuario usuarioConcluidor; // Quem concluiu a tarefa

    private boolean resgatada;
}