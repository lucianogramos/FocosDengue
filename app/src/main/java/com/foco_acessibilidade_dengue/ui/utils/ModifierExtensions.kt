package com.foco_acessibilidade_dengue.ui.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws a dashed border on top of the content.
 */
fun Modifier.dashedBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp = 1.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
) = this.drawWithContent {
    drawContent()
    val outline = shape.createOutline(size, layoutDirection, this)
    val pathEffect = PathEffect.dashPathEffect(
        intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx()),
        phase = 0f
    )
    drawOutline(
        outline = outline,
        color = color,
        style = Stroke(
            width = strokeWidth.toPx(),
            pathEffect = pathEffect
        )
    )
}