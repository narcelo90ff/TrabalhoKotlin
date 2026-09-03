package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(background = Color(0xFF0D0D0D))) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0D0D0D)
                ) {
                    DatingApp()
                }
            }
        }
    }
}

@Composable
fun DatingApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Color(0xFF0D0D0D),
        bottomBar = {
            NavigationBar(containerColor = Color(0xFF141414)) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Text("🔥", fontSize = 20.sp) },
                    label = { Text("Descobrir") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Text("❤", fontSize = 20.sp) },
                    label = { Text("Curtidas") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Text("💬", fontSize = 20.sp) },
                    label = { Text("Chat") }
                )
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { Text("👤", fontSize = 20.sp) },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (selectedTab) {
                0 -> SwipeScreen()
                1 -> LikesScreen()
                2 -> ChatScreen()
                3 -> ProfileScreen()
            }
        }
    }
}

@Composable
fun SwipeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Descobrir", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)

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
                "Lara, 18",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1A1A))) {
                Text("✕", color = Color(0xFFFE3C72), fontSize = 22.sp)
            }
            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1A1A))) {
                Text("♥", color = Color(0xFF3FDC7C), fontSize = 22.sp)
            }
        }
    }
}

// Modelo simples só para alimentar as listas de Curtidas e Chat
data class Person(val name: String, val age: Int, val subtitle: String)

@Composable
fun LikesScreen() {
    val curtidas = remember {
        listOf(
            Person("Beatriz", 24, "Curtiu você de volta!"),
            Person("Camila", 27, "Match perfeito!")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Curtidas", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(
                "${curtidas.size} curtida${if (curtidas.size != 1) "s" else ""}",
                color = Color(0xFF8A8A8A),
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(curtidas) { pessoa ->
                CardItem(name = pessoa.name, age = pessoa.age, subtitle = pessoa.subtitle)
            }
        }
    }
}

@Composable
fun ChatScreen() {
    val matches = remember { listOf("Beatriz", "Camila", "Julia") }
    val conversas = remember {
        listOf(
            Person("Beatriz", 24, "Beatriz: Oii! Tudo bem?"),
            Person("Camila", 27, "Você: Combinado então 😄")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("Chat", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Novos matches", color = Color(0xFF8A8A8A), fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(matches) { nome ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFFF7A8A))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(nome, color = Color.White, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Mensagens", color = Color(0xFF8A8A8A), fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(conversas) { pessoa ->
                CardItem(name = pessoa.name, age = pessoa.age, subtitle = pessoa.subtitle)
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Seu Perfil",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFFFF7A8A)),
            contentAlignment = Alignment.Center
        ) {
            Text("Foto", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(30.dp))
        Text("Nome: Você", color = Color.White, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Idade: 23 anos", color = Color.White, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Localização: São Paulo, SP", color = Color.White, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFE3C72)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Editar perfil", color = Color.White)
        }
    }
}

@Composable
fun CardItem(name: String, age: Int, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1A1A1A))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFF8A7AFF))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("$name, $age", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(subtitle, color = Color(0xFF8A8A8A), fontSize = 14.sp)
        }
    }
}
