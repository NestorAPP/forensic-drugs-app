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
            title = "Растения",
            subtitle = "Постановления № 1002 и № 934 + смежные виды"
        )

        Spacer(Modifier.height(8.dp))

        if (selected == null) {
            SectionTitle("Официальный перечень (Постановления № 1002 и № 934)")
            PlantsRepository.plants.filter { it.inOfficialList }.forEach { plant ->
                PlantListCard(plant) { selected = it }
            }

            Spacer(Modifier.height(12.dp))

            SectionTitle("Смежные растения (не в перечне)")
            PlantsRepository.plants.filter { !it.inOfficialList }.forEach { plant ->
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
private fun SectionTitle(text: String) {
    Text(
        text,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun PlantListCard(plant: Plant, onClick: (Plant) -> Unit) {
    val cardColor = if (plant.inOfficialList) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick(plant) }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Text(
                    plant.name,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                if (!plant.inOfficialList) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            "вне перечня",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
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
        if (!plant.inOfficialList) {
            Surface(
                color = Color(0xFFFFF3E0),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Внимание: растение НЕ входит в перечень Постановлений № 1002 и № 934. По ст. 231 УК РФ не квалифицируется. Может фигурировать по ст. 234 (ядовитые/сильнодействующие) или как сырьё для кустарного изготовления.",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF5D4037)
                )
            }
            Spacer(Modifier.height(10.dp))
        }

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

    if (plant.inOfficialList && plant.significant > 0.0) {
        GreenSectionCard(title = "Масса (Постановление № 1002)") {
            PlantSizeRow("Значительный", formatMass(plant.significant))
            PlantSizeRow("Крупный", formatMass(plant.large))
            PlantSizeRow("Особо крупный", formatMass(plant.extraLarge))
            Spacer(Modifier.height(8.dp))
            Text(
                "Масса определяется после высушивания до постоянной массы при +110…+115 °C. Применяется для ст. 228, 228.1, 229, 229.1 УК РФ.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    if (plant.hasCultivationLimits) {
        GreenSectionCard(title = "Культивирование (ст. 231 УК РФ)") {
            PlantSizeRow("Крупный размер", "от ${plant.cultivationLarge} ${plant.cultivationUnit}")
            PlantSizeRow("Особо крупный размер", "от ${plant.cultivationExtraLarge} ${plant.cultivationUnit}")
            Spacer(Modifier.height(8.dp))
            Text(
                "Размер определяется независимо от фазы развития растений. Установлено Постановлением № 934 от 27.11.2010.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
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
        grams <= 0.0 -> "—"
        grams >= 1000 -> "${grams / 1000.0} кг"
        grams >= 1 -> "$grams г"
        grams >= 0.001 -> "${grams * 1000} мг"
        else -> "$grams г"
    }
}
