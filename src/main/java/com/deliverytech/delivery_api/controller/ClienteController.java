package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente criado = clienteService.cadastrar(cliente);
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

}
