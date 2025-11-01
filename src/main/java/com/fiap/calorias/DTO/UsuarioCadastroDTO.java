package com.fiap.calorias.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
        Long usuarioId,
        @NotBlank(message = "O nome de usuario é obrigatorio")
        String nome,

        @NotBlank(message = "O email é obrigatorio")
        @Email(message = "O email passado não é valido")
        String email,

        @NotBlank(message = "O nome de usuario é obrigatorio")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres")
        String senha
) {
}
