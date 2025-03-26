package br.com.jogoemequipe.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @Email
        @NotBlank
        String email,

        @NotBlank
        String name,

        @NotBlank
        @Size(min = 8)
        String password
) {}
