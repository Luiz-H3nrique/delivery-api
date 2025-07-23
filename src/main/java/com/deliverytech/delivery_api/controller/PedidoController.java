package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.pedido.ItemRequest;
import com.deliverytech.delivery_api.dto.request.pedido.PedidoRequest;
import com.deliverytech.delivery_api.dto.response.pedido.PedidoResponse;
import com.deliverytech.delivery_api.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@RequestBody @Valid PedidoRequest pedidoRequest) {

        PedidoResponse novoPedido = pedidoService.criar(pedidoRequest);
        URI location = URI.create("/pedidos/" + novoPedido.id());
       return ResponseEntity
                .created(location)
                .body(novoPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {

        return  pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponse>> listarPorCliente(@PathVariable Long clienteId) {
        List<PedidoResponse> pedidos = pedidoService.listarPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<PedidoResponse>> listarPorRestaurante(@PathVariable Long restauranteId) {
        List<PedidoResponse> pedidos = pedidoService.listarPorRestaurante(restauranteId);
        return ResponseEntity.ok(pedidos);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponse> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        PedidoResponse pedidoAtualizado = pedidoService.atualizarStatus(id, status);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @PostMapping("/{pedidoId}/itens")
    public ResponseEntity<PedidoResponse> adicionarItem(
            @PathVariable Long pedidoId,
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade,
            @RequestParam(required = false) String observacoes
    ) {
        ItemRequest itemRequest = new ItemRequest(produtoId, quantidade, observacoes);
        PedidoResponse pedido = pedidoService.adicionarItem(pedidoId, itemRequest);

        return ResponseEntity
                .ok()
                .body(pedido);
    }


    @PutMapping("/{id}/confirmar")
    public ResponseEntity<PedidoResponse> confirmarPedido(@PathVariable Long id) {
        PedidoResponse pedidoConfirmado = pedidoService.confirmarPedido(id);
        return ResponseEntity.ok(pedidoConfirmado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizarPedido(@PathVariable Long id, @RequestBody @Valid PedidoRequest pedidoRequest) {
        PedidoResponse pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoRequest);
        return ResponseEntity.ok(pedidoAtualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        pedidoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

}
