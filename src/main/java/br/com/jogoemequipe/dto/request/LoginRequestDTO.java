package br.com.jogoemequipe.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para autenticação")
public record LoginRequestDTO(
        @Email(message = "Email Inválido")
        @NotBlank
        @Schema(description = "Email do usuário", example = "joao@sgtur.com.br")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Schema(description = "Senha do usuário")
        String password
) {}
