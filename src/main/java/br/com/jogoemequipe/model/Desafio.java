package br.com.jogoemequipe.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "desafios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Desafio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "usuarios_desafios",
            joinColumns = @JoinColumn(name = "desafio_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> participantes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "criado_por", nullable = false)
    private Usuario criadoPor;
}
