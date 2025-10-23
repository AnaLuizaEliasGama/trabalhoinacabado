package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

class ProdutoTest {

    @Test
    void deveAplicarDescontoCorretamente() {
        // Dado
        Produto produto = new Produto(1L, "Notebook", "Eletrônicos",
                new BigDecimal("1000"), new BigDecimal("1200"), 5);

        // Quando
        produto.aplicarDesconto(new BigDecimal("10")); // 10%

        // Então
        assertEquals(new BigDecimal("1080.00"), produto.getPrecoVenda());
    }

    @Test
    void deveLancarExcecaoSePrecoVendaMenorQueCusto() {
        // Dado
        Produto produto = new Produto(1L, "Notebook", "Eletrônicos",
                new BigDecimal("1000"), new BigDecimal("1200"), 5);

        // Quando / Então
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produto.setPrecoVenda(new BigDecimal("900"));
        });

        assertEquals("Preço de venda não pode ser menor que o custo.", exception.getMessage());
    }

    @Test
    void deveCalcularMargemLucroCorretamente() {
        // Dado
        Produto produto = new Produto(1L, "Notebook", "Eletrônicos",
                new BigDecimal("1000"), new BigDecimal("1200"), 5);

        // Quando
        BigDecimal margem = produto.calcularMargemLucro();

        // Então
        assertEquals(new BigDecimal("20.00"), margem.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void deveReporEstoqueCorretamente() {
        // Dado
        Produto produto = new Produto(1L, "Notebook", "Eletrônicos",
                new BigDecimal("1000"), new BigDecimal("1200"), 5);

        // Quando
        produto.reporEstoque(10);

        // Então
        assertEquals(15, produto.getEstoque());
    }

    @Test
    void deveDarBaixaEstoqueCorretamente() {
        // Dado
        Produto produto = new Produto(1L, "Notebook", "Eletrônicos",
                new BigDecimal("1000"), new BigDecimal("1200"), 5);

        // Quando
        produto.darBaixaEstoque(3);

        // Então
        assertEquals(2, produto.getEstoque());
    }

    @Test
    void deveLancarExcecaoSeDarBaixaEmEstoqueInsuficiente() {
        // Dado
        Produto produto = new Produto(1L, "Notebook", "Eletrônicos",
                new BigDecimal("1000"), new BigDecimal("1200"), 5);

        // Quando / Então
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            produto.darBaixaEstoque(10);
        });

        assertEquals("Estoque insuficiente.", exception.getMessage());
    }
}