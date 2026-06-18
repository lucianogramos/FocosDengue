package com.focos_dengue.ui.main_screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.focos_dengue.ui.theme.AppTheme
import kotlin.math.abs

@SuppressLint("FrequentlyChangingValue")
@Composable
fun VerticalScrollableContent(
    bottomBar: (@Composable AnimatedVisibilityScope.() -> Unit)? = null,
    content: @Composable (PaddingValues, ScrollState) -> Unit
) {
    val scrollState = rememberScrollState()
    var isBottomBarVisible by remember { mutableStateOf(true) }
    var lastScrollValue by remember { mutableIntStateOf(0) }

    val errorMargin = 10

    // Efeito para observar a mudança na rolagem
    LaunchedEffect(scrollState.value) {
        val value = scrollState.value
        val isAtBottom = value >= scrollState.maxValue
        val isAtTop = value <= 0

        if (isAtTop)
            isBottomBarVisible = true
        else if (abs(value - lastScrollValue) > errorMargin && !isAtBottom)
            isBottomBarVisible = value < lastScrollValue

        lastScrollValue = value
    }

    Scaffold(
        bottomBar =
            if (bottomBar != null) {
                {
                    AnimatedVisibility(
                        visible = isBottomBarVisible,
                        enter = slideInVertically(initialOffsetY = { it }), // Desliza de baixo para cima
                        exit = slideOutVertically(targetOffsetY = { it }),  // Desliza para baixo ao sair
                    ) {
                        bottomBar()
                    }
                }
            }
            else {
                {}
            }
    ) { innerPadding ->
        content(innerPadding, scrollState)
    }
}

@Composable
fun RowScope.BottomBarButton(text: String, shape: Shape, isActive: Boolean, onClick: (() -> Unit)? = null) {
    val colors = AppTheme.colors
    var modifier = Modifier.fillMaxSize().weight(1f).background(
        color = if (isActive) colors.secondary else colors.background,
        shape = shape
    )

    if (onClick != null)
        modifier = modifier.clickable(onClick = onClick)

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = colors.onBackground)
    }
}
