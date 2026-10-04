package com.forensic.drugs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import com.forensic.drugs.ui.screens.ArticlesScreen
import com.forensic.drugs.ui.screens.HintsScreen
import com.forensic.drugs.ui.screens.PlantsScreen
import com.forensic.drugs.ui.screens.SearchScreen
import com.forensic.drugs.ui.screens.SettingsScreen
import com.forensic.drugs.ui.screens.SizeScreen
import com.forensic.drugs.ui.screens.SplashScreen
import com.forensic.drugs.ui.theme.AppThemeMode
import com.forensic.drugs.ui.theme.ForensicDrugsTheme
import com.forensic.drugs.ui.theme.ThemePreference

data class TabItem(val title: String, val icon: ImageVector)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemePreference.load(this)

        setContent {
            val dark = when (ThemePreference.current) {
                AppThemeMode.SYSTEM -> isSystemInDarkTheme()
                AppThemeMode.LIGHT -> false
                AppThemeMode.DARK -> true
            }

            ForensicDrugsTheme(darkTheme = dark) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showSplash by remember { mutableStateOf(true) }
                    if (showSplash) {
                        SplashScreen(onFinished = { showSplash = false })
                    } else {
                        MainScreen()
                    }
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
        TabItem("Размер", Icons.Filled.Info),
        TabItem("Статьи", Icons.Filled.Menu),
        TabItem("Растения", Icons.Filled.Favorite),
        TabItem("Подсказки", Icons.Filled.Check)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        FlagStripe()
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "ВПС Нарко",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { selectedTab = 5 }) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Настройки",
                            tint = Color.White
                        )
                    }
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
                0 -> SearchScreen()
                1 -> SizeScreen()
                2 -> ArticlesScreen()
                3 -> PlantsScreen()
                4 -> HintsScreen()
                5 -> SettingsScreen()
            }
        }
    }
}

@Composable
private fun FlagStripe() {
    Column(
        modifier = Modifier
            .width(24.dp)
            .height(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFF0039A6))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFD52B1E))
        )
    }
}
