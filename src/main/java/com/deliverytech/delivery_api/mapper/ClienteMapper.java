package com.deliverytech.delivery_api.mapper;

import com.deliverytech.delivery_api.dto.request.cliente.ClienteRequest;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteResponse;
import com.deliverytech.delivery_api.model.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequest dto) {
        return Cliente.builder()
                .nome(dto.nome())
                .email(dto.email())
                .telefone(dto.telefone())
                .endereco(dto.endereco())
                .build();
    }

    public static ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.isAtivo()
        );
    }
}
