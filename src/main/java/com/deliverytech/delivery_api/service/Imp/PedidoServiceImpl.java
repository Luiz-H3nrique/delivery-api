package com.deliverytech.delivery_api.service.Imp;

import com.deliverytech.delivery_api.model.Pedido;
import com.deliverytech.delivery_api.model.StatusPedido;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Transactional
    @Override
    public Pedido criar(Pedido pedido) {
        pedido.setStatus(StatusPedido.CRIADO);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId)
                .map(List::of)
                .orElseGet(List::of);
    }

    @Override
    public Pedido atualizarStatus(Long id, StatusPedido status) {
        return pedidoRepository.findById(id)
                .map(p -> {
                    p.setStatus(status);
                    return pedidoRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @Override
    public void cancelar(Long id) {
        pedidoRepository.findById(id).ifPresent(p -> {
            p.setStatus(StatusPedido.CANCELADO);
            pedidoRepository.save(p);
        });
    }
}