package com.deliverytech.delivery_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RestauranteCategoriaRequest(
        @NotBlank(message = "A categoria é obrigatória.")
        String categoria
) {}
