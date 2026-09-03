package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF121212)
                ) {
                    TelaPrincipal()
                }
            }
        }
    }
}

@Composable
fun TelaPrincipal() {
    var abaSelecionada by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.weight(1f)) {
            if (abaSelecionada == 0) {
                TelaDescobrir()
            } else if (abaSelecionada == 1) {
                TelaCurtidas()
            } else if (abaSelecionada == 2) {
                TelaChat()
            } else {
                TelaPerfil()
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E1E1E))
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BotaoMenu("Descobrir", abaSelecionada == 0) { abaSelecionada = 0 }
            BotaoMenu("Curtidas", abaSelecionada == 1) { abaSelecionada = 1 }
            BotaoMenu("Chat", abaSelecionada == 2) { abaSelecionada = 2 }
            BotaoMenu("Perfil", abaSelecionada == 3) { abaSelecionada = 3 }
        }
    }
}

@Composable
fun BotaoMenu(texto: String, selecionado: Boolean, aoClicar: () -> Unit) {
    Text(
        text = texto,
        color = if (selecionado) Color(0xFFFE3C72) else Color.Gray,
        fontSize = 13.sp,
        modifier = Modifier.clickable(onClick = aoClicar)
    )
}

@Composable
fun TelaDescobrir() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Descobrir", color = Color.White, fontSize = 20.sp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFFF7A8A)),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = "Lara, 18",
                color = Color.White,
                fontSize = 22.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {  }) {
                Text("❌")
            }
            Button(onClick = {  }) {
                Text("❤\uFE0F")
            }
        }
    }
}

@Composable
fun TelaCurtidas() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Curtidas", color = Color.White, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LinhaPessoa("Beatriz", 24, "Curtiu voce de volta!")
        Spacer(modifier = Modifier.height(10.dp))
        LinhaPessoa("Camila", 27, "Match perfeito!")
    }
}

@Composable
fun TelaChat() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Chat", color = Color.White, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LinhaPessoa("Beatriz", 24, "Oii! Tudo bem?")
        Spacer(modifier = Modifier.height(10.dp))
        LinhaPessoa("Camila", 27, "Combinado entao")
    }
}

@Composable
fun TelaPerfil() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Meu Perfil", color = Color.White, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color(0xFFFF7A8A))
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Nome: Voce", color = Color.White, fontSize = 16.sp)
        Text(text = "Idade: 23 anos", color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun LinhaPessoa(nome: String, idade: Int, mensagem: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF1E1E1E))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(Color(0xFF8A7AFF))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = "$nome, $idade", color = Color.White, fontSize = 16.sp)
            Text(text = mensagem, color = Color.Gray, fontSize = 13.sp)
        }
    }
}
