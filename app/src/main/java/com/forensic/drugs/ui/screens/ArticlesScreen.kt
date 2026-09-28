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
import com.forensic.drugs.data.CriminalArticle
import com.forensic.drugs.data.ArticlesRepository
import com.forensic.drugs.ui.theme.GreenHeader
import com.forensic.drugs.ui.theme.GreenSectionCard

@Composable
fun ArticlesScreen() {
    var selected by remember { mutableStateOf<CriminalArticle?>(null) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        GreenHeader(
            title = "Статьи УК РФ",
            subtitle = "Ст. 228–234: составы, санкции, подсказки"
        )

        Spacer(Modifier.height(8.dp))

        if (selected == null) {
            ArticlesRepository.articles.forEach { article ->
                ArticleListCard(article) { selected = it }
            }
            Spacer(Modifier.height(16.dp))
        } else {
            selected?.let { article ->
                ArticleDetail(article)
                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = { selected = null },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text("← Назад к списку статей")
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun ArticleListCard(article: CriminalArticle, onClick: (CriminalArticle) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick(article) }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                article.number,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(4.dp))
            Text(
                article.summary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ArticleDetail(article: CriminalArticle) {
    GreenSectionCard(
        title = article.number,
        subtitle = article.title
    ) {
        Text(
            article.summary,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

    GreenSectionCard(title = "Части и санкции") {
        article.parts.forEachIndexed { index, part ->
            if (index > 0) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
            Text(
                "${part.part} — ${part.description}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                part.sanction,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

    GreenSectionCard(title = "Подсказки по квалификации") {
        article.hints.forEach { hint ->
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
