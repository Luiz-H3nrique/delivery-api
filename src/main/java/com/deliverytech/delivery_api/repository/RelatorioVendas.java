package com.deliverytech.delivery_api.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RelatorioVendas {
    String getNomeRestaurante();
    BigDecimal getTotalVendas();
    Long getQuantidadePedidos();
}