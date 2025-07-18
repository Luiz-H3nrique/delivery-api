package com.deliverytech.delivery_api.dto.response;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String categoria,
        boolean disponivel,
        Long restauranteId,
        String nomeRestaurante
) {}