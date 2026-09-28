package com.forensic.drugs.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    // Анимации
    val stampScale = remember { Animatable(3.5f) }
    val stampAlpha = remember { Animatable(0.3f) }
    val stopShake = remember { Animatable(0f) }
    val waveScale = remember { Animatable(0.5f) }
    val waveAlpha = remember { Animatable(0.9f) }
    val wordAlpha = remember { Animatable(0f) }
    val wordOffset = remember { Animatable(30f) }
    val screenAlpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        // 1. Печать "падает" — уменьшается от 3.5x до 1x с ускорением
        stampScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 350, easing = LinearOutSlowInEasing)
        )
        stampAlpha.animateTo(1f, animationSpec = tween(150))

        // 2. Лёгкий отскок/дрожь после удара
        stopShake.animateTo(8f, animationSpec = tween(40))
        stopShake.animateTo(-6f, animationSpec = tween(40))
        stopShake.animateTo(4f, animationSpec = tween(40))
        stopShake.animateTo(-2f, animationSpec = tween(40))
        stopShake.animateTo(0f, animationSpec = tween(40))

        // 3. Волна после удара
        waveAlpha.animateTo(0f, animationSpec = tween(600))
        waveScale.animateTo(2.5f, animationSpec = tween(600))

        // 4. Всплытие слова "наркотик"
        delay(150)
        wordAlpha.animateTo(1f, animationSpec = tween(700, easing = LinearOutSlowInEasing))
        wordOffset.animateTo(0f, animationSpec = tween(700, easing = LinearOutSlowInEasing))

        // 5. Пауза и переход
        delay(700)
        screenAlpha.animateTo(0f, animationSpec = tween(400))
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(screenAlpha.value)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0D3B12),
                        Color(0xFF1B5E20),
                        Color(0xFF0D3B12)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Волна (кольцо, расширяется после удара)
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .scale(waveScale.value)
                    .alpha(waveAlpha.value)
                    .clip(CircleShape)
                    .background(Color(0xFFE53935).copy(alpha = 0.15f))
            )

            Spacer(Modifier.height(20.dp))

            // STOP — печать, падает и впечатывается
            Text(
                text = "STOP",
                color = Color(0xFFE53935),
                fontSize = 76.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 8.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(stampAlpha.value)
                    .graphicsLayer(
                        scaleX = stampScale.value,
                        scaleY = stampScale.value,
                        translationX = stopShake.value
                    )
            )

            Spacer(Modifier.height(6.dp))

            // наркотик — всплывает снизу
            Text(
                text = "наркотик",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 10.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(wordAlpha.value)
                    .graphicsLayer(translationY = wordOffset.value)
            )

            Spacer(Modifier.height(60.dp))

            Text(
                text = "справочник следователя",
                color = Color.White.copy(alpha = wordAlpha.value * 0.5f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 3.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
