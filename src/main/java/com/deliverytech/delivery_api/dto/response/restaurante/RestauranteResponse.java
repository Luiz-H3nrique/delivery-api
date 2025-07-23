package com.deliverytech.delivery_api.dto.response.restaurante;

import com.deliverytech.delivery_api.model.Endereco;

import java.math.BigDecimal;

public record RestauranteResponse(
        Long id,
        String nome,
        Endereco endereco,
        String categoria,
        String telefone,
        BigDecimal taxaEntrega,
        Integer tempoEntregaMinutos,
        BigDecimal avaliacao,
        boolean ativo
) {}