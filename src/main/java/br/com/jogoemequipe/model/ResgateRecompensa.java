package br.com.jogoemequipe.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "resgates_recompensa")
@Getter
@Setter
public class ResgateRecompensa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "recompensa_id")
    private Recompensa recompensa;

    private LocalDateTime dataResgate;
}
