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
    // Анимации
    val flagAlpha = remember { Animatable(0f) }
    val flagOffset = remember { Animatable(-60f) }
    val vpsAlpha = remember { Animatable(0f) }
    val vpsScale = remember { Animatable(0.6f) }
    val narcoAlpha = remember { Animatable(0f) }
    val narcoOffset = remember { Animatable(40f) }
    val subtitleAlpha = remember { Animatable(0f) }
    val stripeProgress = remember { Animatable(0f) }
    val screenAlpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        // 1. Флаг опускается сверху
        flagAlpha.animateTo(1f, animationSpec = tween(400, easing = LinearOutSlowInEasing))
        flagOffset.animateTo(0f, animationSpec = tween(500, easing = LinearOutSlowInEasing))

        // 2. ВПС появляется с масштабированием
        delay(150)
        vpsAlpha.animateTo(1f, animationSpec = tween(400))
        vpsScale.animateTo(1f, animationSpec = tween(500, easing = LinearOutSlowInEasing))

        // 3. Нарко всплывает снизу
        delay(200)
        narcoAlpha.animateTo(1f, animationSpec = tween(500, easing = LinearOutSlowInEasing))
        narcoOffset.animateTo(0f, animationSpec = tween(500, easing = LinearOutSlowInEasing))

        // 4. Подпись
        delay(150)
        subtitleAlpha.animateTo(1f, animationSpec = tween(400))

        // 5. Трёхцветная полоса заполняется
        stripeProgress.animateTo(1f, animationSpec = tween(500, easing = LinearOutSlowInEasing))

        // 6. Пауза и переход
        delay(400)
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
                        Color(0xFF00246B),
                        Color(0xFF0039A6),
                        Color(0xFF00246B)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Мини-флаг РФ
            FlagMini(
                modifier = Modifier
                    .alpha(flagAlpha.value)
                    .graphicsLayer(translationY = flagOffset.value)
            )

            Spacer(Modifier.height(28.dp))

            // ВПС — крупно, белым
            Text(
                text = "ВПС",
                color = Color.White,
                fontSize = 72.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 8.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(vpsAlpha.value)
                    .graphicsLayer(scaleX = vpsScale.value, scaleY = vpsScale.value)
            )

            Spacer(Modifier.height(4.dp))

            // Нарко — красным, всплывает
            Text(
                text = "Нарко",
                color = Color(0xFFD52B1E),
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 10.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .alpha(narcoAlpha.value)
                    .graphicsLayer(translationY = narcoOffset.value)
            )

            Spacer(Modifier.height(40.dp))

            // Подпись
            Text(
                text = "виртуальный помощник следователя",
                color = Color.White.copy(alpha = subtitleAlpha.value * 0.85f),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 3.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "в сфере противодействия наркопреступлениям",
                color = Color.White.copy(alpha = subtitleAlpha.value * 0.6f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )
        }

        // Трёхцветная полоса внизу
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(6.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.33f)
                        .alpha(stripeProgress.value)
                        .background(Color.White)
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.33f)
                        .alpha(stripeProgress.value)
                        .background(Color(0xFF0039A6))
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.34f)
                        .alpha(stripeProgress.value)
                        .background(Color(0xFFD52B1E))
                )
            }
        }
    }
}

@Composable
private fun FlagMini(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(96.dp)
            .height(64.dp)
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
