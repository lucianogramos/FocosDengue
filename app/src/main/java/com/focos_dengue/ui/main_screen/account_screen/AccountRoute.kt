package com.focos_dengue.ui.main_screen.account_screen

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.focos_dengue.FocosDengueApplication
import com.focos_dengue.ui.navigation.ScreenName

fun NavGraphBuilder.accountRoute(navController: NavHostController) {
    composable(route = ScreenName.ACCOUNT.route) {
        val app = LocalContext.current.applicationContext as FocosDengueApplication

        AccountScreen(
            toLoginScreen = {
                navController.navigate(ScreenName.LOGIN.route)
            },
            toReportScreen = {
                navController.navigate(ScreenName.REPORT.route)
            },
            viewModel = viewModel(
                factory = AccountViewModelFactory(app.container.authRepository)
            )
        )
    }
}
