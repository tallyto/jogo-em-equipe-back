package br.com.jogoemequipe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "pontos_usuarios")
@Getter
@Setter
public class PontosUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;

    private int pontos = 0;
}