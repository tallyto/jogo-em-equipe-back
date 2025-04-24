package br.com.jogoemequipe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "recompensas")
@Getter
@Setter
public class Recompensa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome da recompensa não pode estar vazio")
    private String nome;

    @Min(value = 1, message = "O custo em pontos deve ser maior que zero")
    private int custoPontos;

    @ManyToOne
    @JoinColumn(name = "desafio_id", nullable = false)
    @NotNull(message = "A recompensa deve estar associada a um desafio")
    private Desafio desafio;

    private String status;

    private boolean resgatada;
}
