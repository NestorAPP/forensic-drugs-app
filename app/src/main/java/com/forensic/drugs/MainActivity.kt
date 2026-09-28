package com.forensic.drugs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forensic.drugs.ui.theme.ForensicDrugsTheme
import com.forensic.drugs.ui.theme.GreenSectionCard

data class TabItem(val title: String, val icon: ImageVector)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForensicDrugsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf(
        TabItem("Поиск", Icons.Filled.Search),
        TabItem("Размер", Icons.Filled.Scale),
        TabItem("Статьи", Icons.Filled.Gavel),
        TabItem("Растения", Icons.Filled.LocalFlorist),
        TabItem("Подсказки", Icons.Filled.Checklist)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "УК РФ 228–234",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title, fontWeight = FontWeight.Medium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (selectedTab) {
                0 -> PlaceholderScreen("Поиск вещества", "Введите название, формулу или описание — поиск по перечням №681, №964, №1002")
                1 -> PlaceholderScreen("Квалификация по размеру", "Калькулятор массы по Постановлению №76. Значительный, крупный, особо крупный")
                2 -> PlaceholderScreen("Статьи УК РФ", "Ст. 228–234: санкции, подсказки, разграничение составов")
                3 -> PlaceholderScreen("Запрещённые растения", "Перечень растений и расчёт размера культивирования (ст. 231)")
                4 -> PlaceholderScreen("Подсказки по расследованию", "Чек-листы по осмотру, обыску, допросу, экспертизам")
            }
        }
    }
}

@Composable
fun PlaceholderScreen(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreenSectionCard(title = title, subtitle = description) {
            Text(
                "Раздел в разработке. Скоро здесь появится рабочий инструмент.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
