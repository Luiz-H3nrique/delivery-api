package com.deliverytech.delivery_api.service.Imp;


import com.deliverytech.delivery_api.dto.request.ProdutoRequest;
import com.deliverytech.delivery_api.dto.response.ProdutoResponse;
import com.deliverytech.delivery_api.dto.response.produto.ProdutoMaisVendidoResponse;
import com.deliverytech.delivery_api.mapper.ProdutoMapper;
import com.deliverytech.delivery_api.mapper.RestauranteMapper;
import com.deliverytech.delivery_api.model.Produto;
import com.deliverytech.delivery_api.repository.ProdutoRepository;
import com.deliverytech.delivery_api.service.ProdutoService;
import com.deliverytech.delivery_api.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteService restauranteService; // Para associar restaurante ao cadastrar ou atualizar

    @Override
    public ProdutoResponse cadastrar(ProdutoRequest produtoRequest) {
        // Mapear DTO para entidade
        Produto produto = ProdutoMapper.toEntity(produtoRequest);

        // Se ProdutoRequest tem restauranteId, busque restaurante para associar
        restauranteService.buscarPorId(produtoRequest.restauranteId())
                .map(RestauranteMapper::toEntityFromResponse)
                .ifPresent(produto::setRestaurante);

        Produto salvo = produtoRepository.save(produto);

        return ProdutoMapper.toResponse(salvo);
    }

    @Override
    public Optional<ProdutoResponse> buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(ProdutoMapper::toResponse);
    }

    @Override
    public List<ProdutoResponse> buscarPorRestaurante(Long restauranteId) {
        return produtoRepository.findByRestauranteId(restauranteId)
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }


    @Override
    public ProdutoResponse atualizar(Long id, ProdutoRequest produtoAtualizado) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(produtoAtualizado.nome());
        produto.setDescricao(produtoAtualizado.descricao());
        produto.setCategoria(produtoAtualizado.categoria());
        produto.setPreco(produtoAtualizado.preco());

        // Atualiza restaurante associado se informado
        restauranteService.buscarPorId(produtoAtualizado.restauranteId())
                .map(RestauranteMapper::toEntityFromResponse)
                .ifPresent(produto::setRestaurante);

        Produto salvo = produtoRepository.save(produto);

        return ProdutoMapper.toResponse(salvo);
    }

    @Override
    public void alterarDisponibilidade(Long id, boolean disponivel) {
        produtoRepository.findById(id).ifPresent(produto -> {
            produto.setDisponivel(disponivel);
            produtoRepository.save(produto);
        });
    }
    @Override
    public List<ProdutoResponse> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria)
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProdutoMaisVendidoResponse> buscarMaisVendidos() {
        return produtoRepository.produtosMaisVendidos()
                .stream()
                .map(obj -> new ProdutoMaisVendidoResponse(
                        (String) obj[0],
                        ((Number) obj[1]).longValue()
                ))
                .toList();
    }


    @Override
    public List<ProdutoResponse> buscarDisponiveis() {
        return produtoRepository.findByDisponivelTrue()
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }
}
