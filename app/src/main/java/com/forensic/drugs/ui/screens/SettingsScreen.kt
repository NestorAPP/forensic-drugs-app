package com.forensic.drugs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forensic.drugs.ui.theme.AppThemeMode
import com.forensic.drugs.ui.theme.GreenHeader
import com.forensic.drugs.ui.theme.GreenSectionCard
import com.forensic.drugs.ui.theme.ThemePreference

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    var showAbout by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    if (showAbout) {
        AboutScreen(onBack = { showAbout = false })
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        GreenHeader(
            title = "Настройки",
            subtitle = "Персонализация приложения"
        )

        Spacer(Modifier.height(8.dp))

        // --- Тема ---
        GreenSectionCard(
            title = "Тема оформления",
            subtitle = "Светлая, тёмная или системная"
        ) {
            val modes = listOf(
                AppThemeMode.SYSTEM to "Системная",
                AppThemeMode.LIGHT to "Светлая",
                AppThemeMode.DARK to "Тёмная"
            )
            modes.forEach { (mode, label) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = ThemePreference.current == mode,
                        onClick = { ThemePreference.set(context, mode) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        label,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // --- О приложении ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            onClick = { showAbout = true }
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "О приложении",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        "Наименование, версия, разработчик",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    "›",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun AboutScreen(onBack: () -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        GreenHeader(
            title = "О приложении",
            subtitle = "ВПС Нарко"
        )

        Spacer(Modifier.height(8.dp))

        TextButton(
            onClick = onBack,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text("← Назад к настройкам")
        }

        GreenSectionCard(title = "Наименование") {
            Text(
                "ВПС Нарко",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Виртуальный помощник следователя в сфере противодействия наркопреступлениям",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        GreenSectionCard(title = "Версия") {
            Text(
                "1.0",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        GreenSectionCard(title = "Разработчик") {
            Text(
                "Иликбаева Е.С.",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        GreenSectionCard(title = "Организация") {
            Text(
                "Крымский филиал Краснодарского университета МВД России",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        GreenSectionCard(title = "Назначение") {
            Text(
                "Приложение предназначено для сотрудников следственных и оперативных подразделений, работающих по делам, связанным с незаконным оборотом наркотических средств, психотропных веществ, их прекурсоров и аналогов (ст. 228–234 УК РФ).",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}
