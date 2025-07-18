package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.cliente.ClienteNomeRequest;
import com.deliverytech.delivery_api.dto.request.cliente.ClienteRequest;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteRanking;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteResponse;
import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@RequestBody @Valid ClienteRequest cliente) {
        ClienteResponse criado = clienteService.cadastrar(cliente);
        return ResponseEntity.ok(criado);
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> listarAtivos() {
        return ResponseEntity.ok(clienteService.listarAtivos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/nome")
    public ResponseEntity<List<ClienteResponse>> buscarPorNome(@RequestBody ClienteNomeRequest clienteNomeRequest) {
        return ResponseEntity.ok(clienteService.buscarPorNome(clienteNomeRequest.nome()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Cliente atualizado = clienteService.atualizar(id, clienteAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/{id}/status ")
    public ResponseEntity<Void> alternarStatusAtivo(@PathVariable Long id) {
        clienteService.ativarDesativar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ClienteRanking>> rankingClientesPorPedidos() {
        List<ClienteRanking> ranking = clienteService.rankingClientesPorPedidos();
        return ResponseEntity.ok(ranking);
    }
}
