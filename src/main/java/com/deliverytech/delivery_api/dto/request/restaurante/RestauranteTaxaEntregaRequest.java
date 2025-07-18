package com.deliverytech.delivery_api.dto.request.restaurante;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RestauranteTaxaEntregaRequest(
        @NotNull(message = "A taxa de entrega máxima não pode ser nula.")
        BigDecimal taxaEntregaMaxima
) {
}
