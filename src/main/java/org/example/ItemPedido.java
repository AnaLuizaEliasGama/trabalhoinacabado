package org.example;

import java.math.BigDecimal;

public class ItemPedido {

    private Produto produto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    public ItemPedido(Produto produto, Integer quantidade) {
        if (produto == null || quantidade <= 0) {
            throw new IllegalArgumentException("Produto e quantidade devem ser válidos.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = produto.getPrecoVenda();
        this.valorTotal = calcularValorTotal();
    }

    // Getters
    public Produto getProduto() { return produto; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getValorUnitario() { return valorUnitario; }
    public BigDecimal getValorTotal() { return valorTotal; }

    // Método para calcular o valor total do item
    public BigDecimal calcularValorTotal() {
        return this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    }

    @Override
    public String toString() {
        return "Produto: " + produto.getNome() + ", Subtotal: " + valorTotal;
    }
}