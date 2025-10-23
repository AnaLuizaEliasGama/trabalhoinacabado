package org.example;

import java.math.BigDecimal;

public class Produto {

    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private Integer estoque;

    // Construtor, getters e setters (já implementados)

    public void aplicarDesconto(BigDecimal percentualDesconto) {
        if (percentualDesconto == null || percentualDesconto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Percentual de desconto inválido.");
        }
        BigDecimal desconto = this.precoVenda.multiply(percentualDesconto.divide(BigDecimal.valueOf(100)));
        this.precoVenda = this.precoVenda.subtract(desconto);
    }

    public void reporEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
        this.estoque += quantidade;
    }

    public void darBaixaEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
        if (this.estoque < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }
        this.estoque -= quantidade;
    }

    public BigDecimal calcularMargemLucro() {
        return this.precoVenda.subtract(this.precoCusto).divide(this.precoCusto, 2, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }
}