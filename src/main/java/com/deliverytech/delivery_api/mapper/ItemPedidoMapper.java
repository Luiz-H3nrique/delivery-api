package com.deliverytech.delivery_api.mapper;


import com.deliverytech.delivery_api.dto.request.itempedido.ItemPedidoRequest;
import com.deliverytech.delivery_api.dto.response.ItemPedido.ItemPedidoResponse;
import com.deliverytech.delivery_api.model.ItemPedido;
import com.deliverytech.delivery_api.model.Produto;

public class ItemPedidoMapper {

    private ItemPedidoMapper() {
        // Classe utilitária, construtor privado
    }

    public static ItemPedido toEntity(ItemPedidoRequest dto) {
        Produto produto = new Produto();
        produto.setId(dto.produtoId());

        ItemPedido item = ItemPedido.builder()
                .produto(produto)
                .quantidade(dto.quantidade())
                .precoUnitario(dto.precoUnitario())
                .build();

        item.setSubtotal(); // calcula subtotal (quantidade * preço unitário)

        return item;
    }

    public static ItemPedidoResponse toResponse(ItemPedido entity) {
        return new ItemPedidoResponse(
                entity.getId(),
                entity.getProduto().getId(),
                entity.getProduto().getNome(),
                entity.getQuantidade(),
                entity.getPrecoUnitario(),
                entity.getSubtotal()
        );
    }
}
