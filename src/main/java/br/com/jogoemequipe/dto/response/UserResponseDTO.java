package br.com.jogoemequipe.dto.response;

import br.com.jogoemequipe.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

public class UserResponseDTO {
    @Schema(description = "ID exclusivo do usuário", example = "1")
    private Long id;

    @Schema(description = "Email do usuário", example = "joao@sgtur.com.br")
    private String email;

    @Schema(description = "Função do usuário", example = "ADMIN")
    private Set<Role> roles;

    @Schema(description = "Habilitado", example = "true")
    private Boolean isEnabled;

    @Schema(description = "Conta não expirada", example = "true")
    private Boolean isAccountNonExpired;

    @Schema(description = "Conta não bloqueada", example = "true")
    private Boolean isAccountNonLocked;

    @Schema(description = "Credenciais não expiradas", example = "true")
    private Boolean isCredentialsNonExpired;
}
