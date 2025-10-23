package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Long id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private StatusPedido status;

    public Pedido(Long id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.ABERTO;
    }

    public void adicionarItem(Produto produto, Integer quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade);
        this.itens.add(item);
    }

    public BigDecimal calcularValorTotal() {
        return this.itens.stream()
                .map(ItemPedido::calcularValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void finalizarPedido() {
        if (this.status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Pedido já foi finalizado ou cancelado.");
        }
        for (ItemPedido item : itens) {
            item.getProduto().darBaixaEstoque(item.getQuantidade());
        }
        this.status = StatusPedido.FINALIZADO;
    }

    public void cancelarPedido() {
        if (this.status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Pedido já foi finalizado ou cancelado.");
        }
        for (ItemPedido item : itens) {
            item.getProduto().reporEstoque(item.getQuantidade());
        }
        this.status = StatusPedido.CANCELADO;
    }

    public String gerarResumo() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("Cliente: ").append(cliente.getNome()).append("\n");
        resumo.append("Itens:\n");
        for (ItemPedido item : itens) {
            resumo.append(item.toString()).append("\n");
        }
        resumo.append("Total: ").append(calcularValorTotal());
        return resumo.toString();
    }



    public void adicionarItem(Produto produto, Integer quantidade) {
        if (produto == null || quantidade <= 0) {
            throw new IllegalArgumentException("Produto e quantidade devem ser válidos.");
        }
        if (produto.getEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
        }
        ItemPedido item = new ItemPedido(produto, quantidade);
        this.itens.add(item);

}