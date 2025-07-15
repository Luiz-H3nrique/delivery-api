package com.deliverytech.delivery_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RestauranteNomeRequest(
        @NotBlank(message = "O categoria é obrigatório.")
        String nome
) {
}
