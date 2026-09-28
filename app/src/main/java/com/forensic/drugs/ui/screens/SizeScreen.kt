package com.forensic.drugs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forensic.drugs.data.SizeCalculator
import com.forensic.drugs.data.SizeCategory
import com.forensic.drugs.data.SubstanceRepository
import com.forensic.drugs.ui.theme.GreenSectionCard
import com.forensic.drugs.ui.theme.GreenHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SizeScreen() {
    var selectedSubstance by remember { mutableStateOf(SubstanceRepository.substances[0]) }
    var expanded by remember { mutableStateOf(false) }
    var massText by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("г") }

    val units = listOf("мг", "г", "кг")
    val scrollState = rememberScrollState()

    val massGrams: Double? = massText.toDoubleOrNull()?.let { value ->
        when (unit) {
            "мг" -> value / 1000.0
            "кг" -> value * 1000.0
            else -> value
        }
    }

    val result = if (massGrams != null && massGrams >= 0) {
        SizeCalculator.calculate(selectedSubstance, massGrams)
    } else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        GreenHeader(
            title = "Квалификация по размеру",
            subtitle = "Постановление № 1002 от 01.10.2012"
        )

        Spacer(Modifier.height(8.dp))

        GreenSectionCard(
            title = "Вещество",
            subtitle = "Выберите из списка"
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedSubstance.name,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    SubstanceRepository.substances.forEach { substance ->
                        DropdownMenuItem(
                            text = {
                                Column {
                                    Text(substance.name, fontWeight = FontWeight.Medium)
                                    Text(
                                        substance.latinName,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            },
                            onClick = {
                                selectedSubstance = substance
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        GreenSectionCard(
            title = "Масса",
            subtitle = "Введите фактический вес"
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = massText,
                    onValueChange = { massText = it },
                    label = { Text("Значение") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                )
                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = {}
                ) {
                    OutlinedTextField(
                        value = unit,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.width(90.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                        )
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                units.forEach { u ->
                    FilterChip(
                        selected = unit == u,
                        onClick = { unit = u },
                        label = { Text(u) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }
        }

        result?.let { r ->
            val cardColor = when (r.category) {
                SizeCategory.NONE -> MaterialTheme.colorScheme.primaryContainer
                SizeCategory.SIGNIFICANT -> Color(0xFFFFF3E0)
                SizeCategory.LARGE -> Color(0xFFFFE0B2)
                SizeCategory.EXTRA_LARGE -> Color(0xFFFFCDD2)
            }

            val textColor = when (r.category) {
                SizeCategory.NONE -> MaterialTheme.colorScheme.onPrimaryContainer
                SizeCategory.SIGNIFICANT -> Color(0xFF5D4037)
                SizeCategory.LARGE -> Color(0xFF4E342E)
                SizeCategory.EXTRA_LARGE -> Color(0xFFB71C1C)
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        r.categoryText,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        r.article,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = textColor
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Санкция: ${r.sanction}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        r.explanation,
                        style = MaterialTheme.typography.bodySmall,
                        color = textColor.copy(alpha = 0.85f)
                    )
                }
            }

            if (selectedSubstance.note.isNotEmpty()) {
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
                        selectedSubstance.note,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
