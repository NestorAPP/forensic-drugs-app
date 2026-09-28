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
import com.forensic.drugs.data.Plant
import com.forensic.drugs.data.PlantsRepository
import com.forensic.drugs.ui.theme.GreenHeader
import com.forensic.drugs.ui.theme.GreenSectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantsScreen() {
    var selected by remember { mutableStateOf<Plant?>(null) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        GreenHeader(
            title = "Запрещённые растения",
            subtitle = "Постановление № 1002 от 01.10.2012. Ст. 231 УК РФ"
        )

        Spacer(Modifier.height(8.dp))

        if (selected == null) {
            PlantsRepository.plants.forEach { plant ->
                PlantListCard(plant) { selected = it }
            }
            Spacer(Modifier.height(16.dp))
        } else {
            selected?.let { plant ->
                PlantDetail(plant)
                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = { selected = null },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text("← Назад к списку растений")
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun PlantListCard(plant: Plant, onClick: (Plant) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick(plant) }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                plant.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                plant.latinName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PlantDetail(plant: Plant) {
    GreenSectionCard(
        title = plant.name,
        subtitle = plant.latinName
    ) {
        Text(
            "Внешний вид",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(4.dp))
        Text(
            plant.appearance,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

    GreenSectionCard(title = "Размеры (Постановление № 1002)") {
        PlantSizeRow("Значительный", formatMass(plant.significant))
        PlantSizeRow("Крупный", formatMass(plant.large))
        PlantSizeRow("Особо крупный", formatMass(plant.extraLarge))
        Spacer(Modifier.height(8.dp))
        Text(
            "Значительный и крупный размеры применяются для ст. 231 УК РФ (культивирование). Масса определяется после высушивания до постоянной массы при +110…+115 °C.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    GreenSectionCard(title = "Подсказки по фиксации и изъятию") {
        plant.hints.forEach { hint ->
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(
                    "• ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    hint,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun PlantSizeRow(label: String, value: String) {
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
            modifier = Modifier.weight(1f),
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
