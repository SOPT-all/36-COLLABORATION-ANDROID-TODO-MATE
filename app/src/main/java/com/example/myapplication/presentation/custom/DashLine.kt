package com.example.myapplication.presentation.custom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Grey50

@Composable
fun DashLine() {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    Canvas(Modifier.fillMaxWidth().height(1.dp)) {
        drawLine(
            color = Grey50,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}