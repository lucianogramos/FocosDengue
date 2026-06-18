package com.focos_dengue.ui.main_screen.send_report_screen

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.focos_dengue.FocosDengueApplication
import com.focos_dengue.ui.navigation.ScreenName

fun NavGraphBuilder.sendReportRoute(navController: NavHostController) {
    composable(
        route = ScreenName.SEND_REPORT.route
    ) {
        val app = LocalContext.current.applicationContext as FocosDengueApplication

        SendReportScreen(
            toReportScreen = {
                navController.navigate(ScreenName.REPORT.route)
            },
            viewModel = viewModel(
                factory = SendReportViewModelFactory(
                    app.container.submitReportUseCase,
                    app.container.getAddressFromLatLngUseCase
                )
            )
        )
    }
}