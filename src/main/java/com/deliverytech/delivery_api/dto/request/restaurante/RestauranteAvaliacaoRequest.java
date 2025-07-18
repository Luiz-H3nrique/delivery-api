package com.deliverytech.delivery_api.dto.request.restaurante;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RestauranteAvaliacaoRequest(
        @NotNull
        BigDecimal avaliacaoMinima
) {
}
