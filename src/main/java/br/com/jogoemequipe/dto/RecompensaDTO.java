package br.com.jogoemequipe.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class RecompensaDTO {
    private String nome;
    private int custoPontos;
    private UUID desafioId;
}
