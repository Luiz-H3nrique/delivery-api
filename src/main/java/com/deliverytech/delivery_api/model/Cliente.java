package com.deliverytech.delivery_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Endereco endereco;
    private boolean ativo;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore // TODO : Provisorio ate ser aplicado DTO
    private List<Pedido> pedidos;

}