package org.example;

import java.math.BigDecimal;

public class Produto {

    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private Integer estoque;

    public Produto() {}

    public Produto(Long id, String nome, String categoria, BigDecimal precoCusto, BigDecimal precoVenda, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        setPrecoCusto(precoCusto);
        setPrecoVenda(precoVenda);
        setEstoque(estoque);
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public BigDecimal getPrecoCusto() { return precoCusto; }
    public BigDecimal getPrecoVenda() { return precoVenda; }
    public Integer getEstoque() { return estoque; }

    public void setPrecoCusto(BigDecimal precoCusto) {
        if (precoCusto == null || precoCusto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço de custo deve ser positivo.");
        }
        this.precoCusto = precoCusto;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        if (precoVenda == null || precoVenda.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço de venda deve ser positivo.");
        }
        if (precoVenda.compareTo(this.precoCusto) < 0) {
            throw new IllegalArgumentException("Preço de venda não pode ser menor que o custo.");
        }
        this.precoVenda = precoVenda;
    }

    public void setEstoque(Integer estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }
        this.estoque = estoque;
    }

    // Métodos de negócio
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

    private void validarValorPositivo(BigDecimal valor, String mensagem) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(mensagem);
        }


    }

}