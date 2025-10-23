package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Loja {

    private String nome;
    private List<Produto> listaProdutos;
    private List<Cliente> listaClientes;
    private List<Pedido> listaPedidos;

    public Loja(String nome) {
        this.nome = nome;
        this.listaProdutos = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
    }

    public void cadastrarProduto(Produto produto) {
        if (produto == null || produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Produto deve ter um nome válido.");
        }
        this.listaProdutos.add(produto);
    }

    public void cadastrarCliente(Cliente cliente) {
        if (cliente == null || cliente.getCpf() == null) {
            throw new IllegalArgumentException("Cliente deve ter um CPF válido.");
        }
        this.listaClientes.add(cliente);
    }

    public void registrarPedido(Pedido pedido) {
        if (pedido == null || pedido.calcularValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Pedido deve ter pelo menos um item.");
        }
        this.listaPedidos.add(pedido);
    }

    public List<Produto> buscarProdutoPorNome(String nomeBusca) {
        return this.listaProdutos.stream()
                .filter(p -> p.getNome() != null && p.getNome().toLowerCase().contains(nomeBusca.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Cliente buscarClientePorCPF(String cpfBusca) {
        return this.listaClientes.stream()
                .filter(c -> c.getCpf() != null && c.getCpf().equals(cpfBusca))
                .findFirst()
                .orElse(null);
    }

    public BigDecimal gerarRelatorioDeVendas() {
        return this.listaPedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.FINALIZADO)
                .map(Pedido::calcularValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Produto> gerarListaProdutosMaisVendidos() {
        Map<Produto, Integer> contagemVendas = this.listaPedidos.stream()
                .filter(p -> p.getStatus() == StatusPedido.FINALIZADO)
                .flatMap(p -> p.getItens().stream())
                .collect(Collectors.groupingBy(
                        ItemPedido::getProduto,
                        Collectors.summingInt(ItemPedido::getQuantidade)
                ));

        return contagemVendas.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}