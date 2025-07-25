package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.cliente.ClienteEmailRequest;
import com.deliverytech.delivery_api.dto.request.cliente.ClienteNomeRequest;
import com.deliverytech.delivery_api.dto.request.cliente.ClienteRequest;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteRanking;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteResponse;
import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Operações relacionadas ao gerenciamento de clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(
            summary = "Criar um novo cliente",
            description = "Cria um novo cliente com os dados fornecidos"
    )
    @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso")
    @PostMapping
    public ResponseEntity<ClienteResponse> criar(
            @RequestBody @Valid ClienteRequest cliente
    ) {
        ClienteResponse criado = clienteService.cadastrar(cliente);
        return ResponseEntity.ok(criado);
    }

    @Operation(
            summary = "Listar clientes ativos",
            description = "Retorna uma lista de todos os clientes com status ativo"
    )
    @ApiResponse(responseCode = "200", description = "Lista de clientes ativos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarAtivos() {
        return ResponseEntity.ok(clienteService.listarAtivos());
    }

    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna os dados de um cliente a partir do seu ID"
    )
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(
            @Parameter(description = "ID do cliente") @PathVariable Long id
    ) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Buscar clientes por nome",
            description = "Retorna uma lista de clientes cujo nome corresponde ao informado"
    )
    @ApiResponse(responseCode = "200", description = "Clientes encontrados")
    @PostMapping("/nome")
    public ResponseEntity<List<ClienteResponse>> buscarPorNome(
            @RequestBody ClienteNomeRequest clienteNomeRequest
    ) {
        return ResponseEntity.ok(clienteService.buscarPorNome(clienteNomeRequest.nome()));
    }

    @Operation(
            summary = "Atualizar cliente",
            description = "Atualiza os dados de um cliente existente"
    )
    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @Parameter(description = "ID do cliente") @PathVariable Long id,
            @RequestBody Cliente clienteAtualizado
    ) {
        Cliente atualizado = clienteService.atualizar(id, clienteAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
            summary = "Ativar ou desativar cliente",
            description = "Alterna o status de ativo do cliente com base no ID"
    )
    @ApiResponse(responseCode = "204", description = "Status alterado com sucesso")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> alternarStatusAtivo(
            @Parameter(description = "ID do cliente") @PathVariable Long id
    ) {
        clienteService.ativarDesativar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Excluir cliente",
            description = "Remove um cliente do sistema com base no ID"
    )
    @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do cliente") @PathVariable Long id
    ) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Ranking de clientes por pedidos",
            description = "Retorna o ranking dos clientes com mais pedidos realizados"
    )
    @ApiResponse(responseCode = "200", description = "Ranking retornado com sucesso")
    @GetMapping("/ranking")
    public ResponseEntity<List<ClienteRanking>> rankingClientesPorPedidos() {
        List<ClienteRanking> ranking = clienteService.rankingClientesPorPedidos();
        return ResponseEntity.ok(ranking);
    }

    @Operation(
            summary = "Buscar clientes por e-mail",
            description = "Retorna uma lista de clientes com base no e-mail informado"
    )
    @ApiResponse(responseCode = "200", description = "Clientes encontrados com base no e-mail")
    @PostMapping("/email")
    public ResponseEntity<List<ClienteResponse>> buscarPorEmail(
            @RequestBody @Valid ClienteEmailRequest clienteEmailRequest
    ) {
        List<ClienteResponse> clientes = clienteService.buscarPorEmail(clienteEmailRequest.email());
        return ResponseEntity.ok(clientes);
    }
}
