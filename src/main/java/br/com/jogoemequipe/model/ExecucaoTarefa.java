package br.com.jogoemequipe.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "execucoes_tarefa")
@Getter
@Setter
public class ExecucaoTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tarefa_id")
    private Tarefa tarefa;

    @ManyToOne
    @JoinColumn(name = "usuario_executor_id")
    private Usuario usuarioExecutor; // Quem executou a tarefa

    private LocalDateTime dataExecucao;
}
