package br.com.jogoemequipe.model;

import jakarta.persistence.*;

@Entity
@Table(name = "historico_pontos")
public class HistoricoPontos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int variacaoPontos;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}