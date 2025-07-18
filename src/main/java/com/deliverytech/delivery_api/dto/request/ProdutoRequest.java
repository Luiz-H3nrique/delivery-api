package com.deliverytech.delivery_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        String descricao,

        @NotNull(message = "O preço é obrigatório.")
        BigDecimal preco,

        @NotBlank(message = "A categoria é obrigatória.")
        String categoria,

        @NotNull(message = "A disponibilidade é obrigatória.")
        Boolean disponivel,

        @NotNull(message = "O ID do restaurante é obrigatório.")
        Long restauranteId
) {}