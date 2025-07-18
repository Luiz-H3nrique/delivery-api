package com.deliverytech.delivery_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    private BigDecimal valorTotal;

    private String numeroPedido;

    private BigDecimal subtotal;

    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private final LocalDate dataPedido = LocalDate.now();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    @Embedded
    private Endereco enderecoEntrega;

    // Personaliza o setter para itens, para setar a referência bidirecional
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
        if (itens != null) {
            itens.forEach(item -> item.setPedido(this));
        }
    }

    // Método para calcular subtotal somando os subtotais dos itens
    public BigDecimal calcularSubtotal() {
        if (itens == null || itens.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Método para calcular valor total somando subtotal + taxaEntrega (supondo que você tenha taxaEntrega em Restaurante)
    public void calcularValorTotal() {
        BigDecimal taxaEntrega = restaurante != null && restaurante.getTaxaEntrega() != null
                ? restaurante.getTaxaEntrega() : BigDecimal.ZERO;

        this.subtotal = calcularSubtotal();
        this.valorTotal = this.subtotal.add(taxaEntrega);
    }

    @PostPersist
    public void gerarNumeroPedido() {
        if (this.numeroPedido == null) {
            this.numeroPedido = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "-" + this.id;
        }
    }
}