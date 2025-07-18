package com.deliverytech.delivery_api.service.Imp;

import com.deliverytech.delivery_api.dto.request.pedido.PedidoRequest;
import com.deliverytech.delivery_api.dto.response.ProdutoResponse;
import com.deliverytech.delivery_api.dto.response.pedido.PedidoResponse;
import com.deliverytech.delivery_api.dto.response.restaurante.RestauranteResponse;
import com.deliverytech.delivery_api.mapper.PedidoMapper;
import com.deliverytech.delivery_api.mapper.ProdutoMapper;
import com.deliverytech.delivery_api.mapper.RestauranteMapper;
import com.deliverytech.delivery_api.model.*;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.service.ClienteService;
import com.deliverytech.delivery_api.service.PedidoService;
import com.deliverytech.delivery_api.service.ProdutoService;
import com.deliverytech.delivery_api.service.RestauranteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final RestauranteService restauranteService;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             ClienteService clienteService,
                             ProdutoService produtoService,
                             RestauranteService restauranteService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.restauranteService = restauranteService;
    }

    @Transactional
    @Override
    public PedidoResponse criar(PedidoRequest pedidoRequest) {
        // Buscar cliente
        Cliente cliente = clienteService.buscarPorId(pedidoRequest.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        // Buscar restaurante e converter
        RestauranteResponse restauranteResponse = restauranteService.buscarPorId(pedidoRequest.restauranteId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));
        Restaurante restaurante = RestauranteMapper.toEntityFromResponse(restauranteResponse);

        // Mapear pedido
        Pedido pedido = PedidoMapper.toEntity(pedidoRequest);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);

        // Validar e mapear itens
        pedido.getItens().forEach(item -> {
            ProdutoResponse produto = produtoService.buscarPorId(item.getProduto().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + item.getProduto().getId()));

            item.setProduto(ProdutoMapper.toEntityFromResponse(produto));
            item.setPedido(pedido);
            item.setSubtotal();
        });

        pedido.calcularValorTotal();

        Pedido salvo = pedidoRepository.save(pedido);
        return PedidoMapper.toResponse(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PedidoResponse> buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(PedidoMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponse> listarPorCliente(Long clienteId) {
        ProdutoResponse produto = produtoService.buscarPorId(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + clienteId));
        return pedidoRepository.findByClienteId(produto.id())
                .map(pedido -> List.of(PedidoMapper.toResponse(pedido)))
                .orElseGet(List::of);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponse> listarPorRestaurante(Long restauranteId) {
        return pedidoRepository.findByRestauranteId(restauranteId)
                .map(pedido -> List.of(PedidoMapper.toResponse(pedido)))
                .orElseGet(List::of);
    }

    @Transactional
    @Override
    public PedidoResponse atualizarStatus(Long id, String status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        pedido.setStatus(StatusPedido.valueOf(status.toUpperCase()));
        return PedidoMapper.toResponse(pedidoRepository.save(pedido));
    }


    @Transactional
    @Override
    public PedidoResponse atualizarPedido(Long id, PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        // Atualiza o cliente
        Cliente cliente = clienteService.buscarPorId(pedidoRequest.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        // Atualiza o restaurante
        Restaurante restaurante = restauranteService.buscarPorId(pedidoRequest.restauranteId())
                .map(RestauranteMapper::toEntityFromResponse)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));
        pedido.setRestaurante(restaurante);

        // Atualiza endereço e observações
        pedido.setEnderecoEntrega(pedidoRequest.enderecoEntrega());
        pedido.setObservacoes(pedidoRequest.observacoes());

        // Atualiza itens do pedido
        pedido.getItens().clear(); // remove todos os itens antigos
        pedidoRequest.itensPedido().forEach(itemRequest -> {
            ProdutoResponse produto = produtoService.buscarPorId(itemRequest.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + itemRequest.produtoId()));

            ItemPedido item = ItemPedido.builder()
                    .produto(ProdutoMapper.toEntityFromResponse(produto))
                    .quantidade(itemRequest.quantidade())
                    .precoUnitario(itemRequest.precoUnitario())
                    .pedido(pedido)
                    .build();

            item.setSubtotal();
            pedido.getItens().add(item);
        });

        // Recalcula total e salva
        pedido.calcularValorTotal();
        pedidoRepository.save(pedido);
        return PedidoMapper.toResponse(pedido);
    }




    @Transactional
    @Override
    public void cancelar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        if (pedido.getStatus() == StatusPedido.ENTREGUE || pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new IllegalStateException("Não é possível cancelar um pedido já entregue ou cancelado");
        }
        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }
}
