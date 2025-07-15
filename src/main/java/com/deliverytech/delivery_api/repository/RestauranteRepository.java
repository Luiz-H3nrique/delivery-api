package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.model.Pedido;
import com.deliverytech.delivery_api.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {
  List<Restaurante> findByAtivoTrue();
  Optional<Restaurante> findByNome(String nomeRestaurante);
  List<Restaurante> findByCategoria(String categoria);
  List<Restaurante> findAllByOrderByAvaliacaoDesc();
  Optional<Restaurante> findByTaxaEntregaLessThanEqual(BigDecimal taxa);
  List<Restaurante> findTop5ByOrderByNomeAsc();
}
