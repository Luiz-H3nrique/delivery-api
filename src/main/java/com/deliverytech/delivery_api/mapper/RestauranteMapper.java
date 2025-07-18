package com.deliverytech.delivery_api.mapper;

import com.deliverytech.delivery_api.dto.request.restaurante.RestauranteRequest;
import com.deliverytech.delivery_api.dto.response.restaurante.RestauranteResponse;
import com.deliverytech.delivery_api.model.Restaurante;

public class RestauranteMapper {

    // Private constructor to prevent instantiation
    private RestauranteMapper() {
        // Utility class, no instances needed
    }

    public static Restaurante toEntity(RestauranteRequest dto) {
        return Restaurante.builder()
                .nome(dto.nome())
                .categoria(dto.categoria())
                .telefone(dto.telefone())
                .taxaEntrega(dto.taxaEntrega())
                .tempoEntregaMinutos(dto.tempoEntregaMinutos())
                .endereco(dto.endereco())
                .ativo(true)
                .build();
    }

    public static RestauranteResponse toResponse(Restaurante restaurante) {
        return new RestauranteResponse(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getCategoria(),
                restaurante.getTelefone(),
                restaurante.getTaxaEntrega(),
                restaurante.getTempoEntregaMinutos(),
                restaurante.isAtivo()

        );
    }

    public static Restaurante toEntityFromResponse(RestauranteResponse response) {
        return Restaurante.builder()
                .id(response.id())
                .nome(response.nome())
                .categoria(response.categoria())
                .telefone(response.telefone())
                .taxaEntrega(response.taxaEntrega())
                .tempoEntregaMinutos(response.tempoEntregaMinutos())
                .endereco(response.endereco())
                .ativo(response.ativo())
                .build();
    }
}