package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.request.pedido.PedidoRequest;
import com.deliverytech.delivery_api.dto.response.pedido.PedidoResponse;
import com.deliverytech.delivery_api.model.Pedido;
import com.deliverytech.delivery_api.model.StatusPedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    PedidoResponse criar(PedidoRequest pedidoRequest);

    Optional<PedidoResponse> buscarPorId(Long id);

    List<PedidoResponse> listarPorCliente(Long clienteId);

    List<PedidoResponse> listarPorRestaurante(Long restauranteId);

    PedidoResponse atualizarStatus(Long id, String status);

    PedidoResponse atualizarPedido(Long id, PedidoRequest pedidoRequest);

    void cancelar(Long id);
}