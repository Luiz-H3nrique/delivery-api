package com.deliverytech.delivery_api.mapper;

import com.deliverytech.delivery_api.dto.request.RestauranteRequest;
import com.deliverytech.delivery_api.dto.response.RestauranteResponse;
import com.deliverytech.delivery_api.model.Restaurante;

public class RestauranteMapper {

    // Private constructor to prevent instantiation
    private RestauranteMapper() {
        // Utility class, no instances needed
    }

    public static Restaurante toEntity(RestauranteRequest dto) {
        return new Restaurante(
                dto.nome(),
                dto.categoria(),
                dto.telefone(),
                dto.taxaEntrega(),
                dto.tempoEntregaMinutos()
        );
    }

    public static RestauranteResponse toResponse(Restaurante restaurante) {
        return new RestauranteResponse(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getCategoria(),
                restaurante.getTelefone(),
                restaurante.getTaxaEntrega(),
                restaurante.getTempoEntregaMinutos(),
                restaurante.isAtivo()
        );
    }
}