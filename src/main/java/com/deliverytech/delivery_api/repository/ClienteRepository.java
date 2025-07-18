package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.dto.request.cliente.ClienteRequest;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteRanking;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteResponse;
import com.deliverytech.delivery_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Cliente> findByAtivoTrue();
    List<ClienteResponse> findByNomeContainingIgnoreCase(String nome);

    @Query(value = "SELECT c.nome, COUNT(p.id) as total_pedidos " +
            "FROM cliente c " +
            "LEFT JOIN pedido p ON c.id = p.cliente_id " +
            "GROUP BY c.id, c.nome " +
            "ORDER BY total_pedidos DESC " +
            "LIMIT 10", nativeQuery = true)
    List<ClienteRanking> rankingClientesPorPedidos();
}
