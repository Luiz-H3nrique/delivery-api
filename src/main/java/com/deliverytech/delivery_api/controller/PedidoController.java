package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.pedido.ItemRequest;
import com.deliverytech.delivery_api.dto.request.pedido.PedidoRequest;
import com.deliverytech.delivery_api.dto.response.pedido.PedidoResponse;
import com.deliverytech.delivery_api.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Pedidos", description = "Gerenciamento de pedidos realizados por clientes")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(
            summary = "Criar novo pedido",
            description = "Cria um pedido com os itens e dados fornecidos"
    )
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
    @PostMapping
    public ResponseEntity<PedidoResponse> criar(
            @RequestBody @Valid PedidoRequest pedidoRequest
    ) {
        PedidoResponse novoPedido = pedidoService.criar(pedidoRequest);
        URI location = URI.create("/pedidos/" + novoPedido.id());
        return ResponseEntity
                .created(location)
                .body(novoPedido);
    }

    @Operation(
            summary = "Buscar pedido por ID",
            description = "Retorna os dados de um pedido específico"
    )
    @ApiResponse(responseCode = "200", description = "Pedido encontrado")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(
            @Parameter(description = "ID do pedido") @PathVariable Long id
    ) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Listar pedidos por cliente",
            description = "Retorna todos os pedidos realizados por um cliente específico"
    )
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponse>> listarPorCliente(
            @Parameter(description = "ID do cliente") @PathVariable Long clienteId
    ) {
        List<PedidoResponse> pedidos = pedidoService.listarPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @Operation(
            summary = "Listar pedidos por restaurante",
            description = "Retorna todos os pedidos direcionados a um restaurante específico"
    )
    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<PedidoResponse>> listarPorRestaurante(
            @Parameter(description = "ID do restaurante") @PathVariable Long restauranteId
    ) {
        List<PedidoResponse> pedidos = pedidoService.listarPorRestaurante(restauranteId);
        return ResponseEntity.ok(pedidos);
    }

    @Operation(
            summary = "Atualizar status do pedido",
            description = "Altera o status de um pedido para o valor fornecido"
    )
    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponse> atualizarStatus(
            @Parameter(description = "ID do pedido") @PathVariable Long id,
            @Parameter(description = "Novo status do pedido") @RequestParam String status
    ) {
        PedidoResponse pedidoAtualizado = pedidoService.atualizarStatus(id, status);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @Operation(
            summary = "Adicionar item ao pedido",
            description = "Adiciona um novo item ao pedido existente"
    )
    @PostMapping("/{pedidoId}/itens")
    public ResponseEntity<PedidoResponse> adicionarItem(
            @Parameter(description = "ID do pedido") @PathVariable Long pedidoId,
            @Parameter(description = "ID do produto a ser adicionado") @RequestParam Long produtoId,
            @Parameter(description = "Quantidade do produto") @RequestParam Integer quantidade,
            @Parameter(description = "Observações adicionais (opcional)") @RequestParam(required = false) String observacoes
    ) {
        ItemRequest itemRequest = new ItemRequest(produtoId, quantidade, observacoes);
        PedidoResponse pedido = pedidoService.adicionarItem(pedidoId, itemRequest);
        return ResponseEntity.ok(pedido);
    }

    @Operation(
            summary = "Confirmar pedido",
            description = "Confirma um pedido para que ele seja processado"
    )
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<PedidoResponse> confirmarPedido(
            @Parameter(description = "ID do pedido") @PathVariable Long id
    ) {
        PedidoResponse pedidoConfirmado = pedidoService.confirmarPedido(id);
        return ResponseEntity.ok(pedidoConfirmado);
    }

    @Operation(
            summary = "Atualizar dados do pedido",
            description = "Atualiza todas as informações do pedido"
    )
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizarPedido(
            @Parameter(description = "ID do pedido") @PathVariable Long id,
            @RequestBody @Valid PedidoRequest pedidoRequest
    ) {
        PedidoResponse pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoRequest);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @Operation(
            summary = "Cancelar pedido",
            description = "Cancela um pedido com base no ID"
    )
    @ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(
            @Parameter(description = "ID do pedido") @PathVariable Long id
    ) {
        pedidoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
