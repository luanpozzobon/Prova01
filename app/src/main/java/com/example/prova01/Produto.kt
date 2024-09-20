package com.example.prova01

data class Produto(
    var nome: String,
    var categoria: String,
    var preco: Float,
    var qtdEstoque: Int
) {
    companion object {
        val produtos = mutableListOf<Produto>()
    }
}
