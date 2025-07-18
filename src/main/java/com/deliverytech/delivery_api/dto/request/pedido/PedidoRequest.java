package com.deliverytech.delivery_api.dto.request.pedido;

import com.deliverytech.delivery_api.dto.request.itempedido.ItemPedidoRequest;
import com.deliverytech.delivery_api.model.Endereco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PedidoRequest(

        @NotNull(message = "O ID do cliente é obrigatório.")
        Long clienteId,

        @NotNull(message = "O ID do restaurante é obrigatório.")
        Long restauranteId,

        String observacoes,

        @NotNull(message = "O endereço de entrega é obrigatório.")
        Endereco enderecoEntrega,

        @NotNull(message = "A lista de itens não pode ser nula.")
        @Size(min = 1, message = "O pedido deve conter pelo menos um item.")
        List<ItemPedidoRequest> itensPedido

) {}
