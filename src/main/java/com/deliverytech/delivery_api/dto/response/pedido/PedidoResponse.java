package com.deliverytech.delivery_api.dto.response.pedido;

import com.deliverytech.delivery_api.dto.response.ItemPedido.ItemPedidoResponse;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteResponse;
import com.deliverytech.delivery_api.dto.response.restaurante.RestauranteResponse;
import com.deliverytech.delivery_api.model.Endereco;
import com.deliverytech.delivery_api.model.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PedidoResponse(
        Long id,
        ClienteResponse cliente,
        RestauranteResponse restaurante,
        BigDecimal valorTotal,
        String numeroPedido,
        BigDecimal subtotal,
        String observacoes,
        StatusPedido status,
        LocalDate dataPedido,
        Endereco enderecoEntrega,
        List<ItemPedidoResponse> itens
) {}

