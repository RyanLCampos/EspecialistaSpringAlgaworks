package com.github.RyanLCampos.algafood_api.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @JoinColumn(name = "cozinha_id", nullable = false)
    @ManyToOne
    private Cozinha cozinha;

    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    @ManyToOne
    private FormaPagamento formaPagamento;
}
