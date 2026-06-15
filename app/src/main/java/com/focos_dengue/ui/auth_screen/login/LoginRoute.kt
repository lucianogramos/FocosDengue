package com.focos_dengue.ui.auth_screen.login

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.focos_dengue.FocosDengueApplication
import com.focos_dengue.ui.navigation.ScreenName

fun NavGraphBuilder.loginRoute(navController: NavHostController) {
    composable(route = ScreenName.LOGIN.route) {
        val app = LocalContext.current.applicationContext as FocosDengueApplication

        LoginScreen(
            toSignUpScreen = {
                navController.navigate(ScreenName.SIGNUP.route)
            },
            toReportScreen = {
                navController.navigate(ScreenName.REPORT.route)
            },
            viewModel = viewModel(factory = LoginViewModelFactory(app.container.authRepository))
        )
    }
}
