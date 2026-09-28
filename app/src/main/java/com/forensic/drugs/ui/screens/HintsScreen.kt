package com.forensic.drugs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forensic.drugs.data.HintCategory
import com.forensic.drugs.data.HintsRepository
import com.forensic.drugs.ui.theme.GreenHeader
import com.forensic.drugs.ui.theme.GreenSectionCard

@Composable
fun HintsScreen() {
    var selected by remember { mutableStateOf<HintCategory?>(null) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        GreenHeader(
            title = "Подсказки по расследованию",
            subtitle = "Осмотр, обыск, допрос, экспертизы"
        )

        Spacer(Modifier.height(8.dp))

        if (selected == null) {
            HintsRepository.categories.forEach { category ->
                HintCategoryCard(category) { selected = it }
            }
            Spacer(Modifier.height(16.dp))
        } else {
            selected?.let { category ->
                TextButton(
                    onClick = { selected = null },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text("← Назад к разделам")
                }
                Spacer(Modifier.height(8.dp))
                category.sections.forEach { section ->
                    GreenSectionCard(
                        title = section.title,
                        subtitle = section.subtitle
                    ) {
                        section.items.forEach { item ->
                            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                                Text(
                                    "• ",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    item,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun HintCategoryCard(category: HintCategory, onClick: (HintCategory) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick(category) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        category.icon,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    category.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "${category.sections.size} раздела",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
