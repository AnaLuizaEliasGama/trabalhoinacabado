package org.example;

import java.math.BigDecimal;

public class ItemPedido {

    private Produto produto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    public ItemPedido(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = produto.getPrecoVenda();
        this.valorTotal = calcularValorTotal();
    }

    public BigDecimal calcularValorTotal() {
        return this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    }

    @Override
    public String toString() {
        return "Produto: " + produto.getNome() + ", Subtotal: " + valorTotal;
    }
}