package com.deliverytech.delivery_api.dto.request.restaurante;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RestauranteAvaliacaoRequest(
        @NotNull(message = "A avaliação é obrigatória")
        @Positive(message = "A avaliação deve ser um valor positivo")
        BigDecimal avaliacaoMinima
) {
}
