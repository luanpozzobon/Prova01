package com.example.prova01

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson

class ListaProdutosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tela()
        }
    }
}

@Composable
fun Tela() {
    val navController = rememberNavController()

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(navController = navController, startDestination = "lista") {
            composable("lista") { ListaProdutos(navController) }
            composable("detalhes/{produtoJSON}") { backStackEntry ->
                val produtoJSON = backStackEntry.arguments?.getString("produtoJSON")
                val produto = Gson().fromJson(produtoJSON, Produto::class.java)
                Detalhes(navController, produto)
            }
        }
    }
}

@Composable
fun ListaProdutos(navController: NavController) {
    val context = LocalContext.current

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(Produto.produtos) { produto ->
                Row {
                    Text("Produto: ${produto.nome} (${produto.qtdEstoque} unidades!)");
                    Button(onClick = {
                        val produtoJSON = Gson().toJson(produto)
                        navController.navigate("detalhes/$produtoJSON")
                    }) {
                        Text("Detalhes")
                    }
                }
            }
        }

        Button(onClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Voltar!")
        }
    }
}

@Composable
fun Detalhes(navController: NavController, produto: Produto) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Produto: ${produto.nome}", fontSize = 21.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Text("Categoria: ${produto.categoria}")

        Spacer(modifier = Modifier.height(10.dp))

        Text("Preço: R$${produto.preco}")

        Spacer(modifier = Modifier.height(10.dp))

        Text("Quantidade: ${produto.qtdEstoque}")

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Voltar!")
        }
    }
}