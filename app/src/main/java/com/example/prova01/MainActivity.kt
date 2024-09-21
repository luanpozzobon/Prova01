package com.example.prova01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelaCadastro()
        }
    }
}

@Composable
fun TelaCadastro() {
    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var qtdEstoque by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome do Produto") })
        TextField(value = categoria, onValueChange = { categoria = it }, label = { Text("Categoria do Produto") })
        TextField(value = preco, onValueChange = { preco = it }, label = { Text("Preço do Produto") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        TextField(value = qtdEstoque, onValueChange = { qtdEstoque = it }, label = { Text("Quantidade em Estoque") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        Button(onClick = {
            if (nome.isBlank()) {
                Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
                return@Button
            }

            if (categoria.isBlank()) {
                Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
                return@Button
            }

            val precoFloat: Float
            if (preco.isBlank()) {
                Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
                return@Button
            } else {
                precoFloat = preco.toFloat()
            }

            val qtdEstoqueInt: Int
            if (qtdEstoque.isBlank()) {
                Toast.makeText(context, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
                return@Button
            } else {
                qtdEstoqueInt = qtdEstoque.toInt()
            }

            val produto = Produto(nome, categoria, precoFloat, qtdEstoqueInt)
            Produto.produtos.add(produto)

            nome = ""
            categoria = ""
            preco = ""
            qtdEstoque = ""
        }) {
            Text("Cadastrar Produto!")
        }

        Button(onClick = {
            val intent = Intent(context, ListaProdutosActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Lista de Produtos!")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTela() {
    TelaCadastro()
}