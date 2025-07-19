package com.deliverytech.delivery_api.mapper;

import com.deliverytech.delivery_api.model.StatusPedido;

public class StatusPedidoMapper {

    public static StatusPedido mapStatus(String status) {
        return switch (status.toUpperCase()) {
            case "CRIADO" -> StatusPedido.CRIADO;
            case "PENDENTE" -> StatusPedido.PENDENTE;
            case "CONFIRMADO" -> StatusPedido.CONFIRMADO;
            case "PREPARANDO" -> StatusPedido.PREPARANDO;
            case "SAIU_PARA_ENTREGA" -> StatusPedido.SAIU_PARA_ENTREGA;
            case "ENTREGUE" -> StatusPedido.ENTREGUE;
            case "CANCELADO" -> StatusPedido.CANCELADO;
            default -> throw new IllegalArgumentException("Status inválido: " + status);
        };
    }
}
