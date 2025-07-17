package com.deliverytech.delivery_api.dto.response;

import java.math.BigDecimal;

public record RestauranteResponse(
        Long id,
        String nome,
        String endereco,
        String categoria,
        String telefone,
        BigDecimal taxaEntrega,
        Integer tempoEntregaMinutos,
        boolean ativo

) {}