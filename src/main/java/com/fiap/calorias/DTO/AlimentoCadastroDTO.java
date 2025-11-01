package com.fiap.calorias.DTO;

import com.fiap.calorias.model.Alimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record AlimentoCadastroDTO(
        Long alimentoId,

        @NotBlank(message = "O nome de usuario é obrigatorio")
        String nome,

        @NotBlank(message = "A porção é obrigatorio")
        String porcao,
        @PositiveOrZero(message = "O valor deve ser Positivo")
        Double quantidadeProteina,
        @PositiveOrZero(message = "O valor deve ser Positivo")
        Double quantidadeCarboidrato,
        @PositiveOrZero(message = "O valor deve ser Positivo")
        Double quantidadeGorduras

) {
    public AlimentoCadastroDTO(Alimento alimento) {
        this(
                alimento.getAlimentoId(),
                alimento.getNome(),
                alimento.getPorcao(),
                alimento.getQuantidadeProteina(),
                alimento.getQuantidadeCarboidrato(),
                alimento.getQuantidadeGorduras()
        );
    }
}
