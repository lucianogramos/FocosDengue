package com.focos_dengue.ui.main_screen.account_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.main_screen.BottomBarButton
import com.focos_dengue.ui.theme.AppTheme
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.MD
import com.focos_dengue.ui.util.PrimaryDivider
import com.focos_dengue.ui.util.SM

@Composable
fun AccountScreenBottomBar(toReportScreen: () -> Unit) {
    val colors = AppTheme.colors

    Column(modifier = Modifier.navigationBarsPadding()) {
        PrimaryDivider(listOf(
            Color.Transparent,
            colors.outline,
            colors.outline,
            colors.outlineVariant,
            colors.outline,
            colors.outline,
            Color.Transparent
        ))

        BottomAppBar(
            modifier = Modifier.height(4 * MD),
            containerColor = colors.background,
            contentPadding = PaddingValues(horizontal = LG)
        ) {
            BottomBarButton(
                text = "Denúncias",
                shape = RoundedCornerShape(topStart = SM, bottomStart = SM),
                isActive = false,
                onClick = toReportScreen
            )
            BottomBarButton(
                text = "Conta",
                shape = RoundedCornerShape(topEnd = SM, bottomEnd = SM),
                isActive = true
            )
        }
    }
}
