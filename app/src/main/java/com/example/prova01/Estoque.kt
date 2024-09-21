package com.example.prova01

class Estoque {
    companion object {
        val listaProdutos = mutableListOf<Produto>()

        fun adicionarProduto(produto: Produto) {
            listaProdutos.add(produto)
        }

        fun calcularValorTotalEstoque(): Double {
            return listaProdutos.sumOf { it.preco * it.quantidade }
        }

        fun calcularQuantidadeTotalProdutos(): Int {
            return listaProdutos.size
        }
    }
}