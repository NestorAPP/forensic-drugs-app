package com.forensic.drugs.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    val stopAlpha = remember { Animatable(0f) }
    val stopScale = remember { Animatable(0.7f) }
    val wordAlpha = remember { Animatable(0f) }
    val wordOffset = remember { Animatable(40f) }
    val screenAlpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        stopAlpha.animateTo(1f, animationSpec = tween(500, easing = LinearOutSlowInEasing))
        stopScale.animateTo(1f, animationSpec = tween(500, easing = LinearOutSlowInEasing))

        delay(200)
        wordAlpha.animateTo(1f, animationSpec = tween(800, easing = LinearOutSlowInEasing))
        wordOffset.animateTo(0f, animationSpec = tween(800, easing = LinearOutSlowInEasing))

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
            Text(
                text = "STOP",
                color = Color(0xFFE53935),
                fontSize = 72.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 6.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(stopAlpha.value)
                    .graphicsLayer(scaleX = stopScale.value, scaleY = stopScale.value)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "наркотик",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 8.sp,
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
