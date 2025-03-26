package br.com.jogoemequipe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recompensas")
@Getter
@Setter
public class Recompensa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int custoPontos;

    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;
}
