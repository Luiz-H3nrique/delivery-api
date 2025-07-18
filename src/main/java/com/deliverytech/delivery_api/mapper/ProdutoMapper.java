package com.deliverytech.delivery_api.mapper;


import com.deliverytech.delivery_api.dto.request.ProdutoRequest;
import com.deliverytech.delivery_api.dto.response.ProdutoResponse;
import com.deliverytech.delivery_api.model.Produto;

public class ProdutoMapper {

    private ProdutoMapper() {

    }


    public static Produto toEntity(ProdutoRequest dto) {
        if (dto == null) return null;

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setCategoria(dto.categoria());
        produto.setDisponivel(dto.disponivel());
        return produto;
    }

    public static ProdutoResponse toResponse(Produto produto) {

        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.isDisponivel(),
                produto.getRestaurante().getId(),
                produto.getRestaurante().getNome()
        );
    }
    public static Produto toEntityFromResponse(ProdutoResponse response) {
        Produto produto = new Produto();
        produto.setId(response.id());
        produto.setNome(response.nome());
        produto.setDescricao(response.descricao());
        produto.setPreco(response.preco());
        produto.setCategoria(response.categoria());
        produto.setDisponivel(response.disponivel());
        return produto;
    }
}
