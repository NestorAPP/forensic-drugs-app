package com.forensic.drugs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forensic.drugs.data.SubstanceInfo
import com.forensic.drugs.data.SubstanceInfoRepository
import com.forensic.drugs.ui.theme.GreenHeader
import com.forensic.drugs.ui.theme.GreenSectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<SubstanceInfo?>(null) }

    val results = remember(query) { SubstanceInfoRepository.search(query) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        GreenHeader(
            title = "Поиск вещества",
            subtitle = "Русское, латинское, сленговое название или часть"
        )

        Spacer(Modifier.height(8.dp))

        GreenSectionCard(
            title = "Что искать?",
            subtitle = "Начните вводить — найдём совпадения"
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    selected = null
                },
                placeholder = { Text("например: героин, марихуана, соль, ЛСД") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
        }

        if (query.isNotBlank() && results.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text(
                    "Ничего не найдено. Проверьте написание или попробуйте другое название.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (selected == null && results.isNotEmpty()) {
            results.forEach { info ->
                SubstanceResultCard(info) { selected = it }
            }
        }

        selected?.let { info ->
            Spacer(Modifier.height(4.dp))
            SubstanceDetailCard(info)
            Spacer(Modifier.height(16.dp))
            TextButton(
                onClick = { selected = null },
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text("← Назад к результатам поиска")
            }
        }
    }
}

@Composable
private fun SubstanceResultCard(info: SubstanceInfo, onClick: (SubstanceInfo) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick(info) }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                info.substance.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                info.substance.latinName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (info.slang.isNotEmpty()) {
                Spacer(Modifier.height(4.dp))
                Text(
                    "Сленг: ${info.slang.joinToString(", ")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SubstanceDetailCard(info: SubstanceInfo) {
    GreenSectionCard(
        title = info.substance.name,
        subtitle = info.substance.latinName
    ) {
        if (info.slang.isNotEmpty()) {
            DetailRow("Сленговые названия", info.slang.joinToString(", "))
        }
        DetailRow("Перечень", info.listCategory)
        DetailRow("Правовой статус", info.legalStatus)
        Spacer(Modifier.height(8.dp))
        Text(
            "Размеры (Постановление № 1002)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(6.dp))
        DetailRow("Значительный", formatMass(info.substance.significant))
        DetailRow("Крупный", formatMass(info.substance.large))
        DetailRow("Особо крупный", formatMass(info.substance.extraLarge))
        if (info.substance.note.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(
                info.substance.note,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
    ) {
        Text(
            "$label:",
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            value,
            modifier = Modifier.weight(1.3f),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

private fun formatMass(grams: Double): String {
    return when {
        grams >= 1000 -> "${grams / 1000.0} кг"
        grams >= 1 -> "$grams г"
        grams >= 0.001 -> "${grams * 1000} мг"
        else -> "$grams г"
    }
}
