package com.deliverytech.delivery_api.mapper;
import com.deliverytech.delivery_api.dto.request.itempedido.ItemPedidoRequest;
import com.deliverytech.delivery_api.dto.request.pedido.PedidoRequest;
import com.deliverytech.delivery_api.dto.response.pedido.PedidoResponse;
import com.deliverytech.delivery_api.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static Pedido toEntity(PedidoRequest dto) {
        // Verifica se o DTO é nulo
        Cliente cliente = new Cliente();
        cliente.setId(dto.clienteId());

        Restaurante restaurante = new Restaurante();
        restaurante.setId(dto.restauranteId());

        // Cria o pedido base (ainda sem os itens)
        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .restaurante(restaurante)
                .observacoes(dto.observacoes())
                .enderecoEntrega(dto.enderecoEntrega())
                .status(StatusPedido.CRIADO) // Status inicial
                .build();

        // Mapeia os itens do pedido com subtotal calculado
        List<ItemPedido> itens = dto.itensPedido().stream()
                .map(itemDto -> {
                    Produto produto = new Produto();
                    produto.setId(itemDto.produtoId()); // ← agora usando ID do produto

                    ItemPedido item = ItemPedido.builder()
                            .produto(produto)
                            .quantidade(itemDto.quantidade())
                            .precoUnitario(itemDto.precoUnitario())
                            .pedido(pedido) // define o dono do item
                            .build();

                    item.setSubtotal(); // ← calcula subtotal baseado em quantidade e preço

                    return item;
                })
                .collect(Collectors.toList());

        // Adiciona os itens ao pedido
        pedido.setItens(itens);

        // Calcula o valor total (soma subtotais)
        pedido.calcularValorTotal();

        return pedido;
    }

    public static PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                ClienteMapper.toResponse(pedido.getCliente()), // Usa mapper existente
                RestauranteMapper.toResponse(pedido.getRestaurante()),
                pedido.getValorTotal(),
                pedido.getNumeroPedido(),
                pedido.getSubtotal(),
                pedido.getObservacoes(),
                pedido.getStatus(),
                pedido.getDataPedido(),
                pedido.getEnderecoEntrega(),
                pedido.getItens().stream()
                        .map(ItemPedidoMapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

    public static PedidoRequest toRequest(Pedido pedido) {
        List<ItemPedidoRequest> itensRequest = pedido.getItens().stream()
                .map(item -> new ItemPedidoRequest(
                        item.getProduto().getId(),
                        item.getQuantidade(),
                        item.getPrecoUnitario()
                ))
                .toList();

        return new PedidoRequest(
                pedido.getCliente().getId(),
                pedido.getRestaurante().getId(),
                pedido.getObservacoes(),
                pedido.getEnderecoEntrega(),
                itensRequest
        );
    }
}