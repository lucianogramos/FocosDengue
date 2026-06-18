package com.focos_dengue.ui.main_screen.report_screen

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.focos_dengue.FocosDengueApplication
import com.focos_dengue.ui.navigation.ScreenName

fun NavGraphBuilder.reportRoute(navController: NavHostController) {
    composable(
        route = ScreenName.REPORT.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "focosdengue://report-screen"
            }
        )
    ) {
        val app = LocalContext.current.applicationContext as FocosDengueApplication

        ReportScreen(
            toAccountScreen = {
                navController.navigate(ScreenName.ACCOUNT.route)
            },
            viewModel = viewModel(
                factory = ReportScreenViewModelFactory(app.container.getReportsUseCase)
            )
        )
    }
}