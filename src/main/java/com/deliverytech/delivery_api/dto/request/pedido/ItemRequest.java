package com.deliverytech.delivery_api.dto.request.pedido;

import jakarta.validation.constraints.NotNull;

public record ItemRequest(
        @NotNull
        Long produtoId,
        @NotNull
        Integer quantidade,
        String observacoes
) {
}
