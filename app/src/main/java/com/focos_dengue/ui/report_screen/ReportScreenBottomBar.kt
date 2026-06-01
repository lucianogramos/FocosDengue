package com.focos_dengue.ui.report_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.times
import com.focos_dengue.data.remote.model.ScreenName
import com.focos_dengue.ui.util.PrimaryDivider
import com.focos_dengue.ui.util.LG
import com.focos_dengue.ui.util.SM
import com.focos_dengue.ui.util.XL

@Composable
fun ReportScreenBottomBar() {
    val colorScheme = MaterialTheme.colorScheme
    var activeScreen by remember { mutableStateOf(ScreenName.REPORT) }

    Column {
        PrimaryDivider()

        BottomAppBar(
            modifier = Modifier.height(2 * XL),
            containerColor = colorScheme.background,
            contentPadding = PaddingValues(horizontal = LG)
        ) {
            Box(
                modifier = Modifier.fillMaxSize().weight(1f)
                    .background(
                        color = if (activeScreen == ScreenName.REPORT) colorScheme.secondary else colorScheme.background,
                        shape = RoundedCornerShape(topStart = SM, bottomStart = SM)
                    )
                    .clickable(enabled = activeScreen !== ScreenName.REPORT) {
                        activeScreen = ScreenName.REPORT
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("Denúncias", color = colorScheme.onBackground)
            }
            Box(
                modifier = Modifier.fillMaxSize().weight(1f)
                    .background(
                        color = if (activeScreen == ScreenName.ACCOUNT) colorScheme.secondary else colorScheme.background,
                        shape = RoundedCornerShape(topEnd = SM, bottomEnd = SM)
                    )
                    .clickable(enabled = activeScreen !== ScreenName.ACCOUNT) {
                        activeScreen = ScreenName.ACCOUNT
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("Conta", color = colorScheme.onBackground)
            }
        }
    }
}
